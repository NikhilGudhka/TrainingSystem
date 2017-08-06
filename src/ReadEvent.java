/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

/**
 * ReadEvent class extends an abstract class ReadFiles
 */
public class ReadEvent extends ReadFiles
{
    /**
     * Gets the header format for the plan file
     * @return s- string array holding the header format
     */
    @Override
    protected String[] getHeader()
    {
        String[] s = {"year", "event", "property"};
        return s;
    }
}
