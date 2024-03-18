package dao;

import dob.DBContext;
import entity.Account;
import entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//CREATE TABLE Account
//(
//    AccountID      INT PRIMARY KEY,
//    CustomerID     INT,
//    AccountBalance MONEY,
//    AccountType    NVARCHAR(20),
//    Password       NVARCHAR(20)
//    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
//        ON DELETE CASCADE
//
//);
public class DAOAccount extends DBContext {
    // Create add, delete, update, get all and get by id methods
    public void addAccount(Account account) {
        String sql = "INSERT INTO Account VALUES(?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, account.getAccountID());
            ps.setInt(2, account.getCustomerID());
            ps.setDouble(3, account.getAccountBalance());
            ps.setString(4, account.getAccountType());
            ps.setString(5, account.getPassword());
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

    public void updateAccount(Account account) {
        String sql = "UPDATE Account SET CustomerID = ?, AccountBalance = ?, AccountType = ?, Password = ? WHERE AccountID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, account.getCustomerID());
            ps.setDouble(2, account.getAccountBalance());
            ps.setString(3, account.getAccountType());
            ps.setString(4, account.getPassword());
            ps.setInt(5, account.getAccountID());
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

    public void deleteAccount(int accountID) {
        String sql = "DELETE FROM Account WHERE AccountID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountID);
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

    public Account login(String username, String password) {
        Customer customer = new DAOCustomer().getCustomerByName(username);
        if (customer == null) return null;
        String sql = "SELECT * FROM Account WHERE CustomerID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(5).equals(password)) {
                    return new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Account> getAllAccount() {
        String sql = "SELECT * FROM Account";
        ArrayList<Account> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {
        DAOAccount dao = new DAOAccount();
//        List<Account> list = dao.getAllAccount();
//        for (Account account : list) {
//            System.out.println(account);
//        }
//        System.out.println("=====================================");
//        Account account = new Account(1, 1000, "Saving", "123456");
//        dao.addAccount(account);
//        list = dao.getAllAccount();
//        for (Account a : list) {
//            System.out.println(a);
//        }
        System.out.println(dao.login("John Doe", "password"));
    }
}
