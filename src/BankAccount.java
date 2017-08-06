/**
 * Created By Nikhil Gudhka 
 * Copyright © Nikhil Gudhka. All rights reserved.
 */

/**
 * BankAccount class implements a Property interface and handles the bank account for companies
 */
public class BankAccount implements Property
{
    /**
     * @param bankBanlance - A private double field that contains the bank balance for a company
     */
    private double bankBalance;

    /**
     * Class constructor that initializes the bankBalance field to Zero
     */
    public BankAccount()
    {
        this.bankBalance = 0.0;
    }

    /**
     * Gets the bank balance
     * @return bankBalance of type double
     */
    public double getBankBalance()
    {
        return bankBalance;
    }

    /**
     * Adds the bankBalance(profit of company) to the previous bank balance
     * @param bankBalance - the profit of the company
     */
    public void setBankBalance(double bankBalance)
    {
        this.bankBalance += bankBalance;
    }

    /**
     * Calcualtes the profit for the BankAccount over the previous year
     * @return profit - the bankbalance multiplied by interest rate
     */
    @Override
    public double calcProfit()
    {
        double profit;
        profit = bankBalance*0.05;
        return profit;
    }
}
