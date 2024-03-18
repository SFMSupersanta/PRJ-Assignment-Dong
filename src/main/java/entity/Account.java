package entity;
//CREATE TABLE Account (
//                         AccountID INT PRIMARY KEY,
//                         CustomerID INT,
//                         AccountBalance MONEY,
//                         AccountType NVARCHAR(20)
//);
public class Account implements Comparable<Account>{
    @Override
    public int compareTo(Account o) {
        double result = this.getAccountBalance() - o.getAccountBalance();
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
    private int AccountID;
    private int CustomerID;
    private double AccountBalance;
    private String AccountType;
    private String password;

    public Account(int accountID, int customerID, double accountBalance, String accountType, String password) {
        AccountID = accountID;
        CustomerID = customerID;
        AccountBalance = accountBalance;
        AccountType = accountType;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccountID=" + AccountID +
                ", CustomerID=" + CustomerID +
                ", AccountBalance=" + AccountBalance +
                ", AccountType='" + AccountType + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Account() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(int customerID, double accountBalance, String accountType, String password) {
        // Randomize customID from 100 - Integer.MAX_VALUE
        this.AccountID = (int) (Math.random() * (Integer.MAX_VALUE - 100 + 1) + 100);
        CustomerID = customerID;
        AccountBalance = accountBalance;
        AccountType = accountType;
        this.password = password;
    }

    public Account(int AccountID, int CustomerID, double AccountBalance, String AccountType) {
        this.AccountID = AccountID;
        this.CustomerID = CustomerID;
        this.AccountBalance = AccountBalance;
        this.AccountType = AccountType;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public double getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(double AccountBalance) {
        this.AccountBalance = AccountBalance;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }
}
