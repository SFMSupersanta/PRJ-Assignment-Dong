-- If database exist then drop it
use master;
IF EXISTS (SELECT *
           FROM sys.databases
           WHERE name = 'CarService')
    DROP DATABASE CarService;
CREATE DATABASE CarService;
USE CarService;

CREATE TABLE Customer
(
    CustomerID     INT PRIMARY KEY,
    CustomerName   NVARCHAR(50),
    PhoneNumber    INT,
    Address        NVARCHAR(50),
    VehicleName    NVARCHAR(50),
    LicencePlate   NVARCHAR(10),
    RepairDate     DATE,
    WarrantyPeriod DATE
);
CREATE TABLE Account
(
    AccountID      INT PRIMARY KEY,
    CustomerID     INT,
    AccountBalance MONEY,
    AccountType    NVARCHAR(20),
    Password       NVARCHAR(20)
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
        ON DELETE CASCADE

);
CREATE TABLE Invoices
(
    InvoiceID     INT PRIMARY KEY,
    CustomerID    INT,
    InvoiceDate   DATE,
    TotalAmount   MONEY,
    PaymentStatus NVARCHAR(20)
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
        ON DELETE CASCADE
);
CREATE TABLE CarAccessories
(
    AccessoryID   INT PRIMARY KEY,
    AccessoryName NVARCHAR(50),
    Price         MONEY,
    StockQuantity INT
);
CREATE TABLE Revenue
(
    RevenueID INT PRIMARY KEY,
    Date      DATE,
    Amount    MONEY
);

-- Create mock data for all tables
INSERT INTO Customer
VALUES (1, 'John Doe', 1234567890, '1234 Main St', 'Toyota', 'ABC123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (2, 'Jane Doe', 1234567890, '1234 Main St', 'Honda', 'XYZ123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (3, 'John Smith', 1234567890, '1234 Main St', 'Ford', 'DEF123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (4, 'Jane Smith', 1234567890, '1234 Main St', 'Chevrolet', 'GHI123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (5, 'John Doe', 1234567890, '1234 Main St', 'Toyota', 'ABC123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (6, 'Jane Doe', 1234567890, '1234 Main St', 'Honda', 'XYZ123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (7, 'John Smith', 1234567890, '1234 Main St', 'Ford', 'DEF123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (8, 'Jane Smith', 1234567890, '1234 Main St', 'Chevrolet', 'GHI123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (9, 'John Doe', 1234567890, '1234 Main St', 'Toyota', 'ABC123', '2021-01-01', '2022-01-01');
INSERT INTO Customer
VALUES (10, 'Jane Doe', 1234567890, '1234 Main St', 'Honda', 'XYZ123', '2021-01-01', '2022-01-01');

INSERT INTO Account
VALUES (1, 1, 100, 'manager', 'password');
INSERT INTO Account
VALUES (2, 2, 200, 'accountant', 'password');
INSERT INTO Account
VALUES (3, 3, 300, 'customer', 'password');
INSERT INTO Account
VALUES (4, 4, 400, 'customer', 'password');
INSERT INTO Account
VALUES (5, 5, 500, 'customer', 'password');
INSERT INTO Account
VALUES (6, 6, 600, 'customer', 'password');
INSERT INTO Account
VALUES (7, 7, 700, 'customer', 'password');
INSERT INTO Account
VALUES (8, 8, 800, 'customer', 'password');
INSERT INTO Account
VALUES (9, 9, 900, 'customer', 'password');
INSERT INTO Account
VALUES (10, 10, 1000, 'customer', 'password');

INSERT INTO Invoices
VALUES (1, 1, '2021-01-01', 100, 'paid');
INSERT INTO Invoices
VALUES (2, 2, '2021-01-01', 200, 'paid');
INSERT INTO Invoices
VALUES (3, 3, '2021-01-01', 300, 'paid');
INSERT INTO Invoices
VALUES (4, 4, '2021-01-01', 400, 'paid');
INSERT INTO Invoices
VALUES (5, 5, '2021-01-01', 500, 'paid');
INSERT INTO Invoices
VALUES (6, 6, '2021-01-01', 600, 'paid');
INSERT INTO Invoices
VALUES (7, 7, '2021-01-01', 700, 'paid');
INSERT INTO Invoices
VALUES (8, 8, '2021-01-01', 800, 'paid');
INSERT INTO Invoices
VALUES (9, 9, '2021-01-01', 900, 'paid');
INSERT INTO Invoices
VALUES (10, 10, '2021-01-01', 1000, 'paid');

INSERT INTO CarAccessories
VALUES (1, 'Car Cover', 100, 10);
INSERT INTO CarAccessories
VALUES (2, 'Car Mats', 200, 20);
INSERT INTO CarAccessories
VALUES (3, 'Car Wax', 300, 30);
INSERT INTO CarAccessories
VALUES (4, 'Car Polish', 400, 40);
INSERT INTO CarAccessories
VALUES (5, 'Car Shampoo', 500, 50);
INSERT INTO CarAccessories
VALUES (6, 'Car Freshener', 600, 60);
INSERT INTO CarAccessories
VALUES (7, 'Car Seat Cover', 700, 70);
INSERT INTO CarAccessories
VALUES (8, 'Car Perfume', 800, 80);
INSERT INTO CarAccessories
VALUES (9, 'Car Polisher', 900, 90);
INSERT INTO CarAccessories
VALUES (10, 'Car Waxer', 1000, 100);

INSERT INTO Revenue
VALUES (1, '2021-01-01', 100);
INSERT INTO Revenue
VALUES (2, '2021-01-01', 200);
INSERT INTO Revenue
VALUES (3, '2021-01-01', 300);
INSERT INTO Revenue
VALUES (4, '2021-01-01', 400);
INSERT INTO Revenue
VALUES (5, '2021-01-01', 500);
INSERT INTO Revenue
VALUES (6, '2021-01-01', 600);
INSERT INTO Revenue
VALUES (7, '2021-01-01', 700);
INSERT INTO Revenue
VALUES (8, '2021-01-01', 800);
INSERT INTO Revenue
VALUES (9, '2021-01-01', 900);
INSERT INTO Revenue
VALUES (10, '2021-01-01', 1000);

-- Select all data from all tables
SELECT *
FROM Customer;
SELECT *
FROM Account;
SELECT *
FROM Invoices;
SELECT *
FROM CarAccessories;
SELECT *
FROM Revenue;

