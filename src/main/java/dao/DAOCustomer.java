package dao;

import dob.DBContext;
import entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//CREATE TABLE Customer (
//                          CustomerID INT PRIMARY KEY,
//                          CustomerName NVARCHAR(50),
//                          PhoneNumber INT,
//                          Address NVARCHAR(50),
//                          VehicleName NVARCHAR(50),
//                          LicencePlate NVARCHAR(10),
//                          RepairDate DATE,
//                          WarrantyPeriod DATE
//);
public class DAOCustomer extends DBContext {
    // Create add, delete, update, get all and get by id methods
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerID());
            ps.setString(2, customer.getCustomerName());
            ps.setInt(3, customer.getPhoneNumber());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getVehicleName());
            ps.setString(6, customer.getLicencePlate());
            ps.setString(7, customer.getRepairDate());
            ps.setString(8, customer.getWarrantyPeriod());
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

    public Customer getCustomerByName(String name) {
        String sql = "SELECT * FROM Customer WHERE CustomerName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM Customer WHERE CustomerID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
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

    public void updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET CustomerName = ?, PhoneNumber = ?, Address = ?, VehicleName = ?, LicencePlate = ?, RepairDate = ?, WarrantyPeriod = ? WHERE CustomerID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setInt(2, customer.getPhoneNumber());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getVehicleName());
            ps.setString(5, customer.getLicencePlate());
            ps.setString(6, customer.getRepairDate());
            ps.setString(7, customer.getWarrantyPeriod());
            ps.setInt(8, customer.getCustomerID());
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

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Customer getCustomerByID(int id) {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        System.out.println(dao.getAllCustomer());
//        Customer cus = new Customer("Nguyen Van A", 123456789, "Ha Noi", "Toyota", "30A12345", "2021-01-01", "2021-01-01");
//        dao.addCustomer(cus);
//        List<Customer> list = dao.getAllCustomer();
//        for (Customer customer : list) {
//            System.out.println(customer);
//        }
//        System.out.println("===================================");
//        dao.deleteCustomer(cus.getCustomerID());
//        list = dao.getAllCustomer();
//        for (Customer customer : list) {
//            System.out.println(customer);
//        }

    }
}
