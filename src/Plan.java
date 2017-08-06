/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

import java.util.List;

/**
 * Plan class handles the plan events that occur from the start year till the end year
 */
public class Plan
{
    /**
     * @param companies - reference to a list containing the companies
     * @param businessUnits - reference to a list containing the business units
     * @param planFile - reference to a list containing the plan file
     * @param propertyFile - reference to a list containing the property file
     */
    private List<Property> companies;
    private List<Property> businessUnits;
    private List<String[]> planFile;
    private List<String[]> propertyFile;

    /**
     * @param propertyFile - the property file list
     * @param planFile - the plan file list
     * @param companies - the list of companies
     * @param businessUnits - the list of business units
     */
    public Plan(List<String[]> propertyFile, List<String[]> planFile, List<Property> companies, List<Property> businessUnits)
    {
        this.propertyFile = propertyFile;
        this.planFile = planFile;
        this.companies = companies;
        this.businessUnits = businessUnits;
    }

    /**
     * Runs the plans in the plan file for the year given
     * @param year - run the plans for this year
     */
    public boolean runPlan(int year)
    {
        /**
         * @param success - boolean to check there was no error while running a plan
         * @param k - a loop variable that is also used to output line number incase of error
         */
        boolean success = true;
        int k = 0;
        try
        {
            /**
             * Loop through each row in the plan file (i.e loop through each plan in the plan list)
             */
            for (k = 0; k < planFile.size(); k++)
            {
                /**
                 * if the year of the plan is equal to the year passed in then run the plan
                 */
                if (Integer.parseInt(planFile.get(k)[0]) == year)
                {
                    /**
                     * @param property - stores the property from property list that is named in the plan
                     * @param owner - the owner of the property
                     * @param cost - the cost of the property
                     * @param type - the type of property
                     * loop through the property list and find out what type of property the plan is for (i.e company or business unit)
                     * assign type the kind of property it is
                     */
                    Property property = null;
                    String owner = "";
                    double cost = 0.0;
                    String type = "";
                    for (int i = 0; i < propertyFile.size(); i++)
                    {
                        if (planFile.get(k)[2].equals(propertyFile.get(i)[0]))
                        {
                            type = propertyFile.get(i)[1];
                        }
                    }
                    /**
                     * if property is of type company then loop through the company list and initialize the variable with the company information
                     */
                    if (type.toLowerCase().equals("c"))
                    {
                        for (int i = 0; i < companies.size(); i++)
                        {
                            if (planFile.get(k)[2].equals(((Company) companies.get(i)).getName()))
                            {
                                property = companies.get(i);
                                owner = ((Company) companies.get(i)).getOwner();
                                cost = ((Company) companies.get(i)).getWorth();
                            }
                        }
                    }
                    /**
                     * if property is of type business unit then loop through the businessUnit list and initialize the variable with the business unit information
                     */
                    else if (type.toLowerCase().equals("b"))
                    {
                        for (int i = 0; i < businessUnits.size(); i++)
                        {
                            if (planFile.get(k)[2].equals(((BusinessUnit) businessUnits.get(i)).getName()))
                            {
                                property = businessUnits.get(i);
                                owner = ((BusinessUnit) businessUnits.get(i)).getOwner();
                                cost = ((BusinessUnit) businessUnits.get(i)).getWorth();
                            }
                        }
                    }
                    /**
                     * if the plan is to buy the property then check if the property is of type company or business unit,
                     * and set the owner of that property to the primary company.
                     * Add the property to the respective list of properties owned by the primary company and
                     * reduce the bank balance of the primary company by the worth of the property.
                     * if the previous owner was a named owner in the simulation remove the property from its list
                     * of owned properties and increase its bank balance by the worth of the property
                     */
                    if (planFile.get(k)[1].toLowerCase().equals("b"))
                    {
                        /**
                         * if the owner of the property is not the primary company, then the primary company can buy it
                         */
                        if(!((Company) companies.get(0)).getName().equals(owner))
                        {
                            if (property instanceof Company)
                            {
                                ((Company) property).setOwner(((Company) companies.get(0)).getName());
                            }
                            else if (property instanceof BusinessUnit)
                            {
                                ((BusinessUnit) property).setOwner(((Company) companies.get(0)).getName());
                            }
                            ((Company) companies.get(0)).addProperty(property);
                            ((Company) companies.get(0)).setBankBalance((cost * -1));
                            if ((!owner.equals("")))
                            {
                                for (int j = 0; j < companies.size(); j++)
                                {
                                    if ((((Company) companies.get(j)).getName().toLowerCase().equals(owner.toLowerCase())))
                                    {
                                        ((Company) companies.get(j)).removeProperty(property);
                                        ((Company) companies.get(j)).setBankBalance((cost));
                                    }
                                }
                            }
                        }
                    }
                    /**
                     * if the plan is to sell the property then check if the property is of type company or business unit,
                     * set the owner to unnamed buyer if property is of type company or business unit.
                     * remove the property from the list of properties owned by the primary company and increase the
                     * bank balance by the worth of the property
                     */
                    else if (planFile.get(k)[1].toLowerCase().equals("s"))
                    {
                        /**
                         * if the owner of the property is the primary company, then the primary company can sell it
                         */
                        if(((Company) companies.get(0)).getName().equals(owner))
                        {
                            if (property instanceof Company)
                            {
                                ((Company) property).setOwner("");
                            }
                            else if (property instanceof BusinessUnit)
                            {
                                ((BusinessUnit) property).setOwner("");
                            }
                            ((Company) companies.get(0)).removeProperty(property);
                            ((Company) companies.get(0)).setBankBalance((cost));
                        }
                    }
                }
            }
        }
        /**
         * catch NumberFormatException if there was an error while trying to parse to int
         * set success to false if parsing fails
         */
        catch(NumberFormatException e)
        {
            System.out.println("Error parsing to integer in Plan File on line " + (k+2) + " " + e.getMessage());
            success = false;
        }
        /**
         * finally always return the boolean success
         */
        finally
        {
            return success;
        }
    }


}
