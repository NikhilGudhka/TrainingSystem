/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Company class implements a Property interface and stores and retrieves relevant information about companies
 */
public class Company implements Property
{
    /**
     * @param name - the company name
     * @param owner - the owner of the company
     * @param worth - how much the company is worth
     * @param bankAccount - bank account of the property
     * @param ownedCompanies - list of companies owned by this company
     * @param ownedBusinessUnits - list of business units owned by this company
     */
    private String name;
    private String owner;
    private double worth;
    private Property bankAccount;
    private List<Property> ownedCompanies;
    private List<Property> ownedBusinessUnits;

    /**
     * Class Constructor initializes the name, owner and worth while creating objects for bankAccount, ownedCompanies, ownedBusniessUnits
     * @param name - the name of the company
     * @param owner - the name of the owner
     * @param worth - how much the company is worth
     */
    public Company(String name, String owner, double worth)
    {
        this.bankAccount = new BankAccount();
        ownedCompanies = new ArrayList<Property>();
        ownedBusinessUnits = new ArrayList<Property>();
        this.name = name;
        setOwner(owner);
        setWorth(worth);
    }

    /**
     * Gets the name of the company
     * @return name - name of the company
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the name of the owner of the company
     * @return owner - owner of the company
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * Sets the name of the owner of the company
     * @param owner - the owner of the company
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    /**
     * Gets the worth of the company
     * @return worth - the amount the company is worth
     */
    public double getWorth()
    {
        return worth;
    }

    /**
     * Sets the worth of the company
     * @param worth - the new value the company is worth
     */
    public void setWorth(double worth)
    {
        if(worth>0.0)
        {
            this.worth = worth;
        }
        else
        {
            this.worth = 0.0;
        }
    }

    /**
     * Adds the property to the relevant list based on property type
     * @param property - the property that will be added
     */
    public void addProperty(Property property)
    {
        if(property instanceof Company)
        {
            ownedCompanies.add(property);
        }
        else if(property instanceof BusinessUnit)
        {
            ownedBusinessUnits.add(property);
        }
    }

    /**
     * Removes the property from the relevant list based on property type
     * @param property -  the property that needs to be removed
     */
    public void removeProperty(Property property)
    {
        if(property instanceof Company)
        {
            ownedCompanies.remove(property);
        }
        else if(property instanceof BusinessUnit)
        {
            ownedBusinessUnits.remove(property);
        }
    }

    /**
     * Gets the bank balance of the company
     * @return the bank balance of the bank account
     */
    public double getBankBalance()
    {
        return ((BankAccount)bankAccount).getBankBalance();
    }

    /**
     * Sets the bank balance of the company
     * @param amount - sets the new bank balance(added to previous bankbalance)
     */
    public void setBankBalance(double amount)
    {
        ((BankAccount)bankAccount).setBankBalance(amount);
    }

    /**
     * Calculates the profit for the company over the previous year
     * if the company ownes any other companies or business units,
     * add all their profits with the bank balance including interest of this company
     * @return profit - the profit of the company over the previous year
     */
    @Override
    public double calcProfit()
    {
        double profit = 0.0;
        if (!ownedCompanies.isEmpty())
        {
            for (Property property: ownedCompanies)
            {
                profit += property.calcProfit();
            }
        }
        if(!ownedBusinessUnits.isEmpty())
        {
            for (Property property: ownedBusinessUnits)
            {
                profit += property.calcProfit();
            }
        }
        profit += bankAccount.calcProfit();
        return profit;
    }
}
