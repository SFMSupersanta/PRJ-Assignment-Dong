package entity;

import java.sql.Date;

//CREATE TABLE Invoices (
//                          InvoiceID INT PRIMARY KEY,
//                          CustomerID INT,
//                          InvoiceDate DATE,
//                          TotalAmount MONEY,
//                          PaymentStatus NVARCHAR(20)
//);
public class Invoice implements Comparable<Invoice>{
    @Override
    public int compareTo(Invoice o) {
        double result = this.getTotalAmount() - o.getTotalAmount();
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
    private int InvoiceID;
    private int CustomerID;
    private String InvoiceDate;
    private double TotalAmount;
    private String PaymentStatus;

    public Invoice() {
    }

    public Invoice(int customerID, String invoiceDate, double totalAmount, String paymentStatus) {
        // Randomize Invoice ID form 1 to Integer.MAX_VALUE
        this.InvoiceID = (int) (Math.random() * Integer.MAX_VALUE);
        CustomerID = customerID;
        InvoiceDate = invoiceDate;
        TotalAmount = totalAmount;
        PaymentStatus = paymentStatus;
    }

    public Invoice(int InvoiceID, int CustomerID, String InvoiceDate, double TotalAmount, String PaymentStatus) {
        this.InvoiceID = InvoiceID;
        this.CustomerID = CustomerID;
        this.InvoiceDate = InvoiceDate;
        this.TotalAmount = TotalAmount;
        this.PaymentStatus = PaymentStatus;
    }

    public int getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(int InvoiceID) {
        this.InvoiceID = InvoiceID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "InvoiceID=" + InvoiceID +
                ", CustomerID=" + CustomerID +
                ", InvoiceDate='" + InvoiceDate + '\'' +
                ", TotalAmount=" + TotalAmount +
                ", PaymentStatus='" + PaymentStatus + '\'' +
                '}';
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public Date getInvoiceDate() {
        // turn string into sql.Date object then return date
        return Date.valueOf(InvoiceDate);
    }

    public void setInvoiceDate(String InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }
}
