/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

/**
 * CreateProperty class creates a property
 */
public class CreateProperty
{
    /**
     * @param property - private Property object
     */
    private Property property;

    /**
     * Creates a property based on type of property
     * @param propertyDetails - String array holding property related details
     * @return property - returns a property object
     */
    public boolean createProperty(String [] propertyDetails)
    {
        boolean success = true; // check if creating property was successful
        try
        {
            /**
             * @param name - name of the property
             * @param type - type of property ('B' - Business Unit, 'C' - Company)
             * @param owner - name of owner
             * @param worth - how much the property is worth
             * @param revenue - revenue of the property
             * @param wages - wages of the property
             */
            String name = propertyDetails[0];
            String type = propertyDetails[1];
            String owner = propertyDetails[2];
            double worth = 0.0;
            double revenue = 0.0;
            double wages = 0.0;

            /**
             * if the worth in the property file is not empty then parse to double and assign it to worth
             */
            if (!propertyDetails[3].equals(""))
            {
                worth = Double.parseDouble(propertyDetails[3]);
            }
            /**
             * if the revenue in the property file is not empty then parse to double and assign it to revenue
             */
            if (!propertyDetails[4].equals(""))
            {
                revenue = Double.parseDouble(propertyDetails[4]);
            }
            /**
             * if the wages in the property file is not empty then parse to double and assign it to wages
             */
            if (!propertyDetails[5].equals(""))
            {
                wages = Double.parseDouble(propertyDetails[5]);
            }

            /**
             * if property is of type 'C' create a new Company
             * else if property is of type 'B' create a new BusinessUnit
             */
            if (type.toLowerCase().equals("c"))
            {
                property = new Company(name, owner, worth);
            }
            else if (type.toLowerCase().equals("b"))
            {
                property = new BusinessUnit(name, owner, worth, revenue, wages);
            }

        }
        /**
         * catch NumberFormatException if there was an error while trying to parse to double
         * set success to false if parsing fails
         */
        catch (NumberFormatException e)
        {
            System.out.println("Error parsing to double in Property File : " + e.getMessage());
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

    /**
     * getProperty returns the property
     * @return property - the property
     */
    public Property getProperty()
    {
        return property;
    }
}
