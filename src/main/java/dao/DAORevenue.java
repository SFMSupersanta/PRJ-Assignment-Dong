package dao;

import dob.DBContext;
import entity.Revenue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//CREATE TABLE Revenue (
//                         RevenueID INT PRIMARY KEY,
//                         Date DATE,
//                         Amount MONEY
//);
public class DAORevenue extends DBContext {
    // add, update, delete, get, getAll
    public void addRevenue(Revenue revenue) {
        String sql = "INSERT INTO Revenue VALUES(?,?,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, revenue.getRevenueID());
            ps.setDate(2, revenue.getDate());
            ps.setDouble(3, revenue.getAmount());
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

    public void updateRevenue(Revenue revenue) {
        String sql = "UPDATE Revenue SET Date = ?, Amount = ? WHERE RevenueID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, revenue.getDate());
            ps.setDouble(2, revenue.getAmount());
            ps.setInt(3, revenue.getRevenueID());
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

    public void deleteRevenue(int revenueID) {
        String sql = "DELETE FROM Revenue WHERE RevenueID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, revenueID);
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

    public Revenue getRevenue(int revenueID) {
        String sql = "SELECT * FROM Revenue WHERE RevenueID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, revenueID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Revenue(rs.getInt(1), rs.getString(2), rs.getDouble(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Revenue> getAllRevenue() {
        String sql = "SELECT * FROM Revenue";
        ArrayList<Revenue> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Revenue(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DAORevenue dao = new DAORevenue();
        Revenue revenue = new Revenue("2021-01-01", 1000);
        ArrayList<Revenue> list = dao.getAllRevenue();
        System.out.println("=== Before adding ===");
        for (Revenue r : list) {
            System.out.println(r);
        }
        dao.addRevenue(revenue);
        System.out.println("=== After adding ===");
        list = dao.getAllRevenue();
        for (Revenue r : list) {
            System.out.println(r);
        }
        revenue.setAmount(2000);
        dao.updateRevenue(revenue);
        System.out.println("=== After updating ===");
        list = dao.getAllRevenue();
        for (Revenue r : list) {
            System.out.println(r);
        }
        dao.deleteRevenue(revenue.getRevenueID());
        System.out.println("=== After deleting ===");
        list = dao.getAllRevenue();
        for (Revenue r : list) {
            System.out.println(r);
        }
    }
}
