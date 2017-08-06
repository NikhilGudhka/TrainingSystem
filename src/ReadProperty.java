/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

/**
 * ReadProperty class extends an abstract class ReadFiles
 */
public class ReadProperty extends ReadFiles
{
    /**
     * Gets the header format for the property file
     * @return s - string array holding the header format
     */
    @Override
    protected String[] getHeader()
    {
        String[] s = {"name", "type", "owner", "worth", "revenue", "wages"};
        return s;
    }
}
