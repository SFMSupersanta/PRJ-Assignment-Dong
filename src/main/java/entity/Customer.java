package entity;
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
public class Customer implements Comparable<Customer> {
    @Override
    public int compareTo(Customer o) {
        return this.getCustomerName().compareTo(o.getCustomerName());
    }

    private int CustomerID;
    private String CustomerName;
    private int PhoneNumber;
    private String Address;
    private String VehicleName;
    private String LicencePlate;
    private String RepairDate;
    private String WarrantyPeriod;

    public Customer(int customerID, String customerName, int phoneNumber, String address, String vehicleName, String licencePlate, String repairDate, String warrantyPeriod) {
        CustomerID = customerID;
        CustomerName = customerName;
        PhoneNumber = phoneNumber;
        Address = address;
        VehicleName = vehicleName;
        LicencePlate = licencePlate;
        RepairDate = repairDate;
        WarrantyPeriod = warrantyPeriod;
    }

    public Customer() {
    }

    public Customer(String CustomerName, int PhoneNumber, String Address, String VehicleName, String LicencePlate, String RepairDate, String WarrantyPeriod) {
        // Randomize customID from 100 - Integer.MAX_VALUE
        this.CustomerID = (int) (Math.random() * (Integer.MAX_VALUE - 100 + 1) + 100);
        this.CustomerName = CustomerName;
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
        this.VehicleName = VehicleName;
        this.LicencePlate = LicencePlate;
        this.RepairDate = RepairDate;
        this.WarrantyPeriod = WarrantyPeriod;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getVehicleName() {
        return VehicleName;
    }

    public void setVehicleName(String VehicleName) {
        this.VehicleName = VehicleName;
    }

    public String getLicencePlate() {
        return LicencePlate;
    }

    public void setLicencePlate(String LicencePlate) {
        this.LicencePlate = LicencePlate;
    }

    public String getRepairDate() {
        return RepairDate;
    }

    public void setRepairDate(String RepairDate) {
        this.RepairDate = RepairDate;
    }

    public String getWarrantyPeriod() {
        return WarrantyPeriod;
    }

    public void setWarrantyPeriod(String WarrantyPeriod) {
        this.WarrantyPeriod = WarrantyPeriod;
    }

    @Override
    public String toString() {
        return "Customer{" + "CustomerID=" + CustomerID + ", CustomerName=" + CustomerName + ", PhoneNumber=" + PhoneNumber + ", Address=" + Address + ", VehicleName=" + VehicleName + ", LicencePlate=" + LicencePlate;
    }
}
