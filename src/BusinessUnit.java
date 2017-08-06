/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

/**
 * BusinessUnit class implements a Property interface and stores and retrieves relevant information about business units
 */
public class BusinessUnit implements Property
{
    /**
     * @param name - the business units name
     * @param owner - the owner of the business units
     * @param worth - how much the business unit is worth
     * @param revenue - the business units revenue
     * @param wages - the wages for the business unit
     */
    private String name;
    private String owner;
    private double worth;
    private double revenue;
    private double wages;

    /**
     * Class Constructor initializes the relevant fields
     * @param name - the business units name
     * @param owner - the owner of the business units
     * @param worth - how much the business unit is worth
     * @param revenue - the business units revenue
     * @param wages - the wages for the business unit
     */
    public BusinessUnit(String name, String owner, double worth, double revenue, double wages)
    {
        this.name = name;
        setOwner(owner);
        setWorth(worth);
        setRevenue(revenue);
        setWages(wages);
    }

    /**
     * Gets the name of the business unit
     * @return name - name of business unit
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the name of the owner of the business unit
     * @return owner - owner of business unit
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * Sets the name of the owner of the business unit
     * @param owner - the owner of the business unit
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    /**
     * Gets the worth of the business unit
     * @return worth - the amount the business unit is worth
     */
    public double getWorth()
    {
        return worth;
    }

    /**
     * Sets the worth of the business unit
     * @param worth - the new value the business unit is worth
     */
    public void setWorth(double worth)
    {
        this.worth = worth;
    }

    /**
     * Gets the revenue of the business unit
     * @return revenue - the revenue of the business unit
     */
    public double getRevenue()
    {
        return revenue;
    }

    /**
     * Sets the revenue of the business unit
     * @param revenue - the new revenue of the business unit
     */
    public void setRevenue(double revenue)
    {
        this.revenue = revenue;
    }

    /**
     * Gets the wages of the business unit
     * @return wages - the wages of the business unit
     */
    public double getWages()
    {
        return wages;
    }

    /**
     * Sets the wages of the business unit
     * @param wages - the new wages of the business unit
     */
    public void setWages(double wages)
    {
        this.wages = wages;
    }

    /**
     * Calculates the profit for the business unit over the previous year
     * @return profit - the revenue minus wages
     */
    @Override
    public double calcProfit()
    {
        double profit = revenue - wages;
        return profit;
    }
}
