package entity;
//CREATE TABLE CarAccessories (
//                                AccessoryID INT PRIMARY KEY,
//                                AccessoryName NVARCHAR(50),
//                                Price MONEY,
//                                StockQuantity INT
//);
public class CarAccessories implements Comparable<CarAccessories>{

    private int AccessoryID;
    private String AccessoryName;
    private double Price;
    private int StockQuantity;

    @Override
    public String toString() {
        return "CarAccessories{" +
                "AccessoryID=" + AccessoryID +
                ", AccessoryName='" + AccessoryName + '\'' +
                ", Price=" + Price +
                ", StockQuantity=" + StockQuantity +
                '}';
    }

    public void setAccessoryID(int accessoryID) {
        AccessoryID = accessoryID;
    }

    public String getAccessoryName() {
        return AccessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        AccessoryName = accessoryName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        StockQuantity = stockQuantity;
    }

    @Override
    public int compareTo(CarAccessories o) {
        return this.getAccessoryName().compareTo(o.getAccessoryName());
    }
    public CarAccessories() {
    }

    public CarAccessories(String accessoryName, double price, int stockQuantity) {
        //auto generate id from 1-interger.max
        AccessoryID = (int) (Math.random() * Integer.MAX_VALUE);
        AccessoryName = accessoryName;
        Price = price;
        StockQuantity = stockQuantity;
    }

    public CarAccessories(int AccessoryID, String AccessoryName, double Price, int StockQuantity) {
        this.AccessoryID = AccessoryID;
        this.AccessoryName = AccessoryName;
        this.Price = Price;
        this.StockQuantity = StockQuantity;
    }

    public int getAccessoryID() {
        return AccessoryID;
    }
}
