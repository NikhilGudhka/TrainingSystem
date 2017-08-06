/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * abstract class ReadFiles, a template method to read different files
 */
public abstract class ReadFiles
{
    /**
     * A method to read a file marked final hence subclasses cannot override and change the structure of the method
     * @param filename - the name of the file to open to read
     * @param arrayList - the array list where the contents of the file will be stored
     * @return readSuccess - a boolean stating if the file was read successfully or not
     */
    final boolean readFile(String filename, List<String[]> arrayList)
    {
        boolean readSuccess = false;
        try
        {
            /**
             * open the file for reading by creating a file reader and wraps it with a buffered reader to decrease the cost of reading the file
             * read the first line and splits it by commas and stores the split string in a string array called line
             * calls teh getHeader method and stores the header information in a string array called header
             * sets a boolean variable correct to be true
             */
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String [] line = br.readLine().split(",");
            String [] header = getHeader();
            boolean correct = true;
            /**
             * check the first line of the file against the header to make sure the file has the relevant columns and in the correct order
             * if the line doesn't match the header then set correct to false
             */
            for (int i = 0; i < line.length; i++)
            {
                if(!header[i].equals(line[i].toLowerCase()))
                {
                    correct = false;
                }
            }
            /**
             * if correct is true, declare and initialize i to zero and set readSuccess to true
             * keep reading the file unitl its not null or readSuccess is false
             * split each line by commas storing blanks too
             * if the line length is not equal to the header length then output error message and set readSuccess to false
             * else add the line to the ith index of the arraylist
             * increment i by 1
             */
            if(correct == true)
            {
                String check;
                int i = 0;
                readSuccess = true;
                while (((check = br.readLine()) != null) && (readSuccess ==true))
                {
                    line = check.split("," , -1);
                    if(line.length!=header.length)
                    {
                        System.out.println("Incorrect File Format in " + filename+ " at line: " + (i+2));
                        readSuccess =false;
                    }
                    else
                    {
                        arrayList.add(i, line);
                    }
                    i++;
                }

            }
            /**
             * correct equals false, output error message showing what format the file should be in since header and line don't match
             */
            else
            {
                System.out.print(filename + " is not in correct format, please ensure the file is in the following format: ");
                System.out.print(header[0]);
                for(int k=1;k<header.length;k++)
                {
                    System.out.print(","+header[k]);
                }
                System.out.println();
            }
            br.close(); // close the file
        }
        /**
         * catch FileNotFoundException if the file given as an argument to the program cannot be found
         */
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found : " + e.getMessage());
        }
        /**
         * catch IOException if there was any error reading the file
         */
        catch (IOException e)
        {
            System.out.println("Error Reading File : " + e.getMessage());
        }
        /**
         * finally always return the boolean variable readSuccess
         */
        finally
        {
            return readSuccess;
        }

    }

    /**
     * abstract method that every subclass that extends ReadFiles must implement
     * @return a string array containing the relevant header information of the different files
     */
    protected abstract String[] getHeader();

}
