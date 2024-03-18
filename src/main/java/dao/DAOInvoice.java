package dao;

import dob.DBContext;
import entity.Customer;
import entity.Invoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//CREATE TABLE Invoices (
//                          InvoiceID INT PRIMARY KEY,
//                          CustomerID INT,
//                          InvoiceDate DATE,
//                          TotalAmount MONEY,
//                          PaymentStatus NVARCHAR(20)
//);
public class DAOInvoice extends DBContext {
    public double generateRevenueReport(int year, int month) {
        double revenue = 0;
        String sql = "SELECT SUM(TotalAmount) AS Revenue\n" +
                "FROM Invoices\n" +
                "WHERE YEAR(InvoiceDate) = ? AND MONTH(InvoiceDate) = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, month);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                revenue = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenue;
    }

    // add, update, delete, get, getAll
    //search invoices according to customer's name and date
    public ArrayList<Invoice> searchInvoices(String keyword){
        ArrayList<Invoice> list = new ArrayList<>();
        String sql = "SELECT I.*\n" +
                "FROM Invoices AS I\n" +
                "         JOIN Customer AS C ON I.CustomerID = C.CustomerID\n" +
                "WHERE C.CustomerName LIKE ? OR I.InvoiceDate LIKE ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new Invoice(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void addInvoice(Invoice invoice){
        String sql = "INSERT INTO Invoices VALUES(?,?,?,?,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, invoice.getInvoiceID());
            ps.setInt(2, invoice.getCustomerID());
            ps.setDate(3, invoice.getInvoiceDate());
            ps.setDouble(4, invoice.getTotalAmount());
            ps.setString(5, invoice.getPaymentStatus());
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateInvoice(Invoice invoice){
        String sql = "UPDATE Invoices SET CustomerID = ?, InvoiceDate = ?, TotalAmount = ?, PaymentStatus = ? WHERE InvoiceID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, invoice.getCustomerID());
            ps.setDate(2, invoice.getInvoiceDate());
            ps.setDouble(3, invoice.getTotalAmount());
            ps.setString(4, invoice.getPaymentStatus());
            ps.setInt(5, invoice.getInvoiceID());
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public void deleteInvoice(int invoiceID){
        String sql = "DELETE FROM Invoices WHERE InvoiceID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, invoiceID);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public Invoice getInvoice(int invoiceID) {
        String sql = "SELECT * FROM Invoices WHERE InvoiceID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, invoiceID);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Invoice(resultSet.getInt("InvoiceID"), resultSet.getInt("CustomerID"), resultSet.getString("InvoiceDate"), resultSet.getDouble("TotalAmount"), resultSet.getString("PaymentStatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Invoice> getAllInvoices() {
        ArrayList<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM Invoices";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                invoices.add(new Invoice(resultSet.getInt("InvoiceID"), resultSet.getInt("CustomerID"), resultSet.getString("InvoiceDate"), resultSet.getDouble("TotalAmount"), resultSet.getString("PaymentStatus")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public static void main(String[] args) {
        DAOInvoice dao = new DAOInvoice();
        System.out.println(dao.generateRevenueReport(2021, 1));
//        Invoice invoice = new Invoice(1, "2021-01-01", 1000, "Paid");
//        ArrayList<Invoice> invoices = dao.getAllInvoices();
//        for (Invoice i : invoices) {
//            System.out.println(i);
//        }
//        System.out.println("==================================");
//        dao.addInvoice(invoice);
//        invoices = dao.getAllInvoices();
//        for (Invoice i : invoices) {
//            System.out.println(i);
//        }
//        System.out.println("==================================");
//        invoice.setTotalAmount(2000);
//        dao.updateInvoice(invoice);
//        invoices = dao.getAllInvoices();
//        for (Invoice i : invoices) {
//            System.out.println(i);
//        }
//        System.out.println("==================================");
//        dao.deleteInvoice(invoice.getInvoiceID());
//        invoices = dao.getAllInvoices();
//        for (Invoice i : invoices) {
//            System.out.println(i);
//        }
    }

    public ArrayList<Invoice> searchInvoicesByCustomer(Customer customer) {
        ArrayList<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM Invoices WHERE CustomerID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerID());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new Invoice(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Invoice> searchInvoicesByCustomerIDAndKeyWord(Customer customer, String search) {
        ArrayList<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM Invoices WHERE CustomerID = ? AND (InvoiceDate LIKE ? OR TotalAmount LIKE ? OR PaymentStatus LIKE ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerID());
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setString(4, "%" + search + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new Invoice(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
