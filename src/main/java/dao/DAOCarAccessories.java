package dao;
import dob.DBContext;
import entity.CarAccessories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//CREATE TABLE CarAccessories (
//                                AccessoryID INT PRIMARY KEY,
//                                AccessoryName NVARCHAR(50),
//                                Price MONEY,
//                                StockQuantity INT
//);
public class DAOCarAccessories extends DBContext {
    // add, update, delete, get, getAll
    public ArrayList<CarAccessories> searchByName(String keyword){
        ArrayList<CarAccessories> list = new ArrayList<>();
        String sql = "SELECT * FROM CarAccessories WHERE AccessoryName LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new CarAccessories(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<CarAccessories> getAllCarAccessories() {
        ArrayList<CarAccessories> list = new ArrayList<>();
        String sql = "SELECT * FROM CarAccessories";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new CarAccessories(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void addCarAccessories(CarAccessories carAccessories) {
        String sql = "INSERT INTO CarAccessories VALUES(?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carAccessories.getAccessoryID());
            ps.setString(2, carAccessories.getAccessoryName());
            ps.setDouble(3, carAccessories.getPrice());
            ps.setInt(4, carAccessories.getStockQuantity());
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

    public void updateCarAccessories(CarAccessories carAccessories) {
        String sql = "UPDATE CarAccessories SET AccessoryName = ?, Price = ?, StockQuantity = ? WHERE AccessoryID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, carAccessories.getAccessoryName());
            ps.setDouble(2, carAccessories.getPrice());
            ps.setInt(3, carAccessories.getStockQuantity());
            ps.setInt(4, carAccessories.getAccessoryID());
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

    public void deleteCarAccessories(int accessoryID) {
        String sql = "DELETE FROM CarAccessories WHERE AccessoryID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accessoryID);
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

    public CarAccessories getCarAccessories(int accessoryID) {
        String sql = "SELECT * FROM CarAccessories WHERE AccessoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accessoryID);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new CarAccessories(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CarAccessories getCarAccessoriesByName(String accessoryName) {
        String sql = "SELECT * FROM CarAccessories WHERE AccessoryName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accessoryName);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new CarAccessories(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DAOCarAccessories daoCarAccessories = new DAOCarAccessories();
        System.out.println(daoCarAccessories.searchByName("Wax"));
    }

}
