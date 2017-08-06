/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

import java.util.List;

/**
 * Event class handles the events that happen from the the start year till the end year
 */
public class Events
{
    /**
     * @param companies - reference to a list containing the companies
     * @param businessUnits - reference to a list containing the business units
     * @param planFile - reference to a list containing the plan file
     * @param propertyFile - reference to a list containing the property file
     */
    private List<Property> companies;
    private List<Property> businessUnits;
    private List<String[]> eventFile;
    private List<String[]> propertyFile;

    /**
     * @param propertyFile - the property file list
     * @param eventFile - the plan file list
     * @param companies - the list of companies
     * @param businessUnits - the list of business units
     */
    public Events(List<String[]> propertyFile, List<String[]> eventFile, List<Property> companies, List<Property> businessUnits)
    {
        this.propertyFile = propertyFile;
        this.eventFile = eventFile;
        this.companies = companies;
        this.businessUnits = businessUnits;
    }

    public boolean runEvents(int year)
    {
        /**
         * @param success - boolean to check there was no error while running an event
         * @param k - a loop variable that is also used to output line number incase of error
         */
        boolean success = true;
        int k =0 ;
        try
        {
            /**
             * Loop through each row in the event file (i.e loop through each event in the event list)
             */
            for (k = 0; k < eventFile.size(); k++)
            {
                /**
                 * if the year of the event is equal to the year passed in then run the event
                 */
                if (Integer.parseInt(eventFile.get(k)[0]) == year)
                {
                    /**
                     * Run events of type w+ or w- or r+ or r-
                     */
                    if ((eventFile.get(k)[1].toLowerCase().equals("w+")) || (eventFile.get(k)[1].toLowerCase().equals("w-"))
                            || (eventFile.get(k)[1].toLowerCase().equals("r+")) || (eventFile.get(k)[1].toLowerCase().equals("r-")))
                    {
                        /**
                         * if event is of type w+ increase wages for all business units by 5 percent
                         * if event is of type w- decrease wages for all business units by 5 percent
                         * if event is of type r+ increase relevant for the specified business unit in the event file by 5 percent
                         * if event is of type r- decrease relevant for the specified business unit in the event file by 5 percent
                         */
                        for (int j = 0; j < businessUnits.size(); j++)
                        {
                            if (eventFile.get(k)[1].toLowerCase().equals("w+"))
                            {
                                ((BusinessUnit) (businessUnits.get(j))).setWages((((BusinessUnit) (businessUnits.get(j))).getWages()) * 1.05);
                            }
                            else if (eventFile.get(k)[1].toLowerCase().equals("w-"))
                            {
                                ((BusinessUnit) (businessUnits.get(j))).setWages(((((BusinessUnit) (businessUnits.get(j)))).getWages()) * 0.95);
                            }
                            else if ((eventFile.get(k)[1].toLowerCase().equals("r+")) && (((BusinessUnit) (businessUnits.get(j))).getName().equals(eventFile.get(k)[2])))
                            {
                                ((BusinessUnit) (businessUnits.get(j))).setRevenue((((BusinessUnit) (businessUnits.get(j))).getRevenue()) * 1.05);
                            }
                            else if ((eventFile.get(k)[1].toLowerCase().equals("r-")) && (((BusinessUnit) (businessUnits.get(j))).getName().equals(eventFile.get(k)[2])))
                            {
                                ((BusinessUnit) (businessUnits.get(j))).setRevenue(((((BusinessUnit) (businessUnits.get(j)))).getRevenue()) * 0.95);
                            }
                        }
                    }
                    /**
                     * Run events for type v+ or v-
                     */
                    else if ((eventFile.get(k)[1].toLowerCase().equals("v+")) || (eventFile.get(k)[1].toLowerCase().equals("v-")))
                    {
                        /**
                         * @param type - the type of property
                         * loop through the property list and find out what type of property the event is for (i.e company or business unit)
                         * assign type the kind of property it is
                         */
                        String type = "";
                        for (int i = 0; i < propertyFile.size(); i++)
                        {
                            if (eventFile.get(k)[2].equals(propertyFile.get(i)[0]))
                            {
                                type = propertyFile.get(i)[1];
                            }
                        }
                        /**
                         * if type of property is company increase the worth of the company by 5 percent if the event is of type v+
                         * else decrease the worth of the company by 5 percent if the event is of type v-
                         */
                        if (type.toLowerCase().equals("c"))
                        {
                            for (int j = 0; j < companies.size(); j++)
                            {
                                if ((eventFile.get(k)[1].toLowerCase().equals("v+")) && (((Company) (companies.get(j))).getName().equals(eventFile.get(k)[2])))
                                {
                                    ((Company) (companies.get(j))).setWorth((((Company) (companies.get(j))).getWorth()) * 1.05);
                                }
                                else if ((eventFile.get(k)[1].toLowerCase().equals("v-")) && (((Company) (companies.get(j))).getName().equals(eventFile.get(k)[2])))
                                {
                                    ((Company) (companies.get(j))).setWorth(((((Company) (companies.get(j)))).getWorth()) * 0.95);
                                }
                            }
                        }
                        /**
                         * if type of property is business unit increase the worth of the business unit by 5 percent if the
                         * event is of type v+ else decrease the worth of the business unit by 5 percent if the event is of type v-
                         */
                        else if (type.toLowerCase().equals("b"))
                        {
                            for (int j = 0; j < businessUnits.size(); j++)
                            {
                                if ((eventFile.get(k)[1].toLowerCase().equals("v+")) && (((BusinessUnit) (businessUnits.get(j))).getName().equals(eventFile.get(k)[2])))
                                {
                                    ((BusinessUnit) (businessUnits.get(j))).setWorth((((BusinessUnit) (businessUnits.get(j))).getWorth()) * 1.05);
                                }
                                else if ((eventFile.get(k)[1].toLowerCase().equals("v-")) && (((BusinessUnit) (businessUnits.get(j))).getName().equals(eventFile.get(k)[2])))
                                {
                                    ((BusinessUnit) (businessUnits.get(j))).setWorth(((((BusinessUnit) (businessUnits.get(j)))).getWorth()) * 0.95);
                                }
                            }
                        }
                    }
                }
            }
        }
        /**
         * catch NumberFormatException if there was an error while trying to parse to int
         * set success to false if parsing fails
         */
        catch (NumberFormatException e)
        {
            System.out.println("Error parsing to integer in Event File on line " + (k+2) + " " + e.getMessage());
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


