/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * RunSimulation class runs the training simulation system for company directors
 */
public class RunSimulation
{
    /**
     * Main class where the programs start execution
     * @param args - the arguments given to the program
     */
    public static void main(String [] args)
    {
        /**
         * check if the arguments are equal to 5, if not output error message
         */
        if(args.length != 5)
        {
            System.out.println("Too Few or many Arguments given");
        }
        else
        {
            try
            {
                /**
                 * Check if the starting year is less then the end year
                 */
                if(Integer.parseInt(args[3])<= Integer.parseInt(args[4]))
                {
                    /**
                     * @param propertyFile - An array list that stores the property file
                     * @param eventFile - An array list to store the event file
                     * @param planFile - An array list to store the plan file
                     * @param companies - An array list to store all the companies
                     * @param businessUnits - An array list to store all the business units
                     * @param createProperty - A CreateProperty object
                     * @param readProperty - A ReadProperty object
                     * @param readEvent - A ReadEvent object
                     * @param readPlan - A ReadPlan object
                     * @param checkProperty - A boolean to check if there was any error while reading the property file
                     * @param checkEvents - A boolean to check if there were any errors while reading the events file
                     * @param checkPlan - A boolean to check if there were any errors while reading the plan file
                     */
                    List<String[]> propertyFile = new ArrayList<String[]>();
                    List<String[]> eventFile = new ArrayList<String[]>();
                    List<String[]> planFile = new ArrayList<String[]>();
                    List<Property> companies = new ArrayList<Property>();
                    List<Property> businessUnits = new ArrayList<Property>();

                    CreateProperty createProperty = new CreateProperty();
                    ReadFiles readProperty = new ReadProperty();
                    ReadFiles readEvent = new ReadEvent();
                    ReadFiles readPlan = new ReadPlan();

                    boolean checkProperty = false;
                    boolean checkEvents = false;
                    boolean checkPlan = false;
                    /**
                     * Read the property, event, plan files respectively
                     * all 3 methods return a boolean stating whether there was any error reading the files
                     */
                    checkProperty = readProperty.readFile(args[0], propertyFile);
                    checkEvents = readEvent.readFile(args[1], eventFile);
                    checkPlan = readPlan.readFile(args[2], planFile);

                    /**
                     * if all the files are read successfully without any error then continue execution
                     */
                    if ((checkProperty == true) && (checkEvents == true) && (checkPlan == true))
                    {
                        /**
                         * @param row - a row in the property file
                         * @param propertySuccess - boolean to check if a property was created successfully
                         */
                        int row = 0;
                        boolean propertySuccess = true;
                        /**
                         * Loop while the row number is less then the propertyFile array list size (row starts from zero hence less then)
                         * and while propertySuccess is true
                         */
                        while ((row < propertyFile.size()) && (propertySuccess == true))
                        {
                            /**
                             * create a property by calling the createProperty method and passing in what property needs to
                             * be created using the row to get the index from the propertyFile array list
                             * returns a boolean stating whether the property was created successfully or not
                             */
                            propertySuccess = createProperty.createProperty(propertyFile.get(row));
                            /**
                             * check if the property was created successfully then continue execution
                             */
                            if (propertySuccess == true)
                            {
                                /**
                                 * Gets the property that was created and stores it in a variable called createdProperty
                                 */
                                Property createdProperty = createProperty.getProperty();
                                /**
                                 * check if property is of type company
                                 */
                                if (createdProperty instanceof Company)
                                {
                                    /**
                                     * add the property to the company array list
                                     */
                                    companies.add(createdProperty);
                                    /**
                                     * check if company has a named owner or not
                                     */
                                    if (!(((Company) createdProperty).getOwner().equals("")))
                                    {
                                        /**
                                         * check if the owner of that property exists in the companies array list and
                                         * add the property under the owners owned companies list if it does exist
                                         */
                                        for (Property eachProperty : companies)
                                        {
                                            if ((((Company) createdProperty).getOwner()).equals(((Company) eachProperty).getName()))
                                            {
                                                ((Company) eachProperty).addProperty(createdProperty);
                                            }
                                        }
                                    }
                                }
                                /**
                                 * check if property is of type business unit
                                 */
                                else if (createdProperty instanceof BusinessUnit)
                                {
                                    /**
                                     * add the property to the business unit array list
                                     */
                                    businessUnits.add(createdProperty);
                                    /**
                                     * check if business unit has a named owner or not
                                     */
                                    if (!(((BusinessUnit) createdProperty).getOwner().equals("")))
                                    {
                                        /**
                                         * check if the owner of that property exists in the companies array list and
                                         * add the property under the owners owned business unit list if it does exist
                                         */
                                        for (Property eachProperty : companies)
                                        {
                                            if ((((BusinessUnit) createdProperty).getOwner()).equals(((Company) eachProperty).getName()))
                                            {
                                                ((Company) eachProperty).addProperty(createdProperty);
                                            }
                                        }
                                    }
                                }
                            }
                            row++; //increment row by 1
                        }

                        /**
                         * continue execution if there was no error in creating all the properties
                         */
                        if (propertySuccess == true)
                        {
                            /**
                             * Print out a standard header to the console letting the user know what each column is for
                             * @param year -  initialized to the start year given as an argument to the program
                             * @param eventSuccess - boolean to check if there was any errors while running events
                             * @param planSuccess - boolean to check if there was any errors while running the plan
                             * @param events - An Events object
                             * @param plan - A Plan object
                             */
                            System.out.format("%4s\t%-30s\t%15s", "Year", "Company Name", "Bank Balance");
                            System.out.println("\n");
                            int year = Integer.parseInt(args[3]);
                            boolean eventSuccess = true;
                            boolean planSuccess = true;

                            Events events = new Events(propertyFile, eventFile, companies, businessUnits);
                            Plan plan = new Plan(propertyFile, planFile, companies, businessUnits);

                            /**
                             * Keep looping while the year is less then the end year and eventSuccess and planSuccess are true
                             */
                            while ((year <= Integer.parseInt(args[4])) && (eventSuccess == true) && (planSuccess == true))
                            {
                                /**
                                 * if the year is the start year then don't calculate any profits
                                 */
                                if (!(year == Integer.parseInt(args[3])))
                                {
                                    /**
                                     * for each company calculate the profit
                                     * if profit is less then or equal to zero subtract the bank balance by the amount of profit
                                     * else if the profit is greater the zero then multiply by 0.5 and add it to the bank balance
                                     */
                                    for (int i = 0; i < companies.size(); i++)
                                    {
                                        double sumOfProfit = companies.get(i).calcProfit();
                                        if (sumOfProfit <= 0)
                                        {
                                            ((Company) companies.get(i)).setBankBalance(sumOfProfit);
                                        }
                                        else if (sumOfProfit > 0)
                                        {
                                            ((Company) companies.get(i)).setBankBalance((sumOfProfit * 0.5));
                                        }
                                    }
                                }
                                /**
                                 * Print out the year, company name and its bank balance for each year in an easy to read format
                                 */
                                for (int k = 0; k < companies.size(); k++)
                                {
                                    System.out.format("%4d\t%-30s\t%15.3f", year, ((Company) companies.get(k)).getName(), ((Company) companies.get(k)).getBankBalance());
                                    System.out.println();
                                }
                                System.out.println();

                                /**
                                 * run the events and plan for the year
                                 */
                                eventSuccess = events.runEvents(year);
                                planSuccess = plan.runPlan(year);
                                year++; //increment year by 1
                            }
                        }
                    }
                }
            }
            /**
             * catch NumberFormatException if there was an error while trying to parse to int
             */
            catch (NumberFormatException e)
            {
                System.out.println("Error parsing command line arguments for years to integer : " + e.getMessage());
            }
        }
    }
}
