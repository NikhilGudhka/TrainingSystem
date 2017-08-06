/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

/**
 * ReadPlan class extends an abstract class ReadFiles
 */
public class ReadPlan extends ReadFiles
{
    /**
     * Gets the header format for the plan file
     * @return s- string array holding the header format
     */
    @Override
    protected String[] getHeader()
    {
        String[] s = {"year", "buy/sell", "property"};
        return s;
    }
}
