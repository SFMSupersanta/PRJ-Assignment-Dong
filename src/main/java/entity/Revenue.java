package entity;

import java.sql.Date;

//CREATE TABLE Revenue (
//                         RevenueID INT PRIMARY KEY,
//                         Date DATE,
//                         Amount MONEY
//);
public class Revenue implements Comparable<Revenue>{
    @Override
    public String toString() {
        return "Revenue{" +
                "revenueID=" + revenueID +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Revenue o) {
        double result = this.getAmount() - o.getAmount();
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public Revenue(String date, double amount) {
        //Randomize Revenue ID form 1 to Integer.MAX_VALUE
        this.revenueID = (int) (Math.random() * Integer.MAX_VALUE);
        this.date = date;
        this.amount = amount;
    }

    private int revenueID;
    private String date;
    private double amount;

    public Revenue() {
    }

    public Revenue(int RevenueID, String Date, double Amount) {
        this.revenueID = RevenueID;
        this.date = Date;
        this.amount = Amount;
    }

    public int getRevenueID() {
        return revenueID;
    }

    public void setRevenueID(int RevenueID) {
        this.revenueID = RevenueID;
    }

    public Date getDate() {
        // turn string into sql.Date object then return date
        return Date.valueOf(date);
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double Amount) {
        this.amount = Amount;
    }

}
