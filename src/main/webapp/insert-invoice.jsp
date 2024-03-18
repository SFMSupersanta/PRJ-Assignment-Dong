<%@ page import="entity.Invoice" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.CarAccessories" %>
<%@ page import="entity.Account" %>
<%@ page import="entity.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Invoice</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        input[type="text"],
        select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .home-btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #28a745; /* Green color */
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s ease;
            margin-bottom: 20px;
        }
        .home-btn:hover {
            background-color: #218838; /* Darker green color */
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Add Invoice</h1>
    <%
        ArrayList<CarAccessories> accessories = (ArrayList<CarAccessories>) request.getAttribute("accessories");
        ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers");
    %>
    <form action="generate-invoice" method="post">
        <label for="customerId">Customer ID:</label>
        <select id="customerId" name="customerId" required>
            <% for (Customer c : customers) { %>
            <option value="<%= c.getCustomerID() %>"><%= c.getCustomerName() %>, <%=c.getLicencePlate()%></option>
            <% } %>
        </select><br>
        <label for="invoiceDate">Invoice Date:</label>
        <input type="text" id="invoiceDate" name="invoiceDate" placeholder="YYYY-MM-DD" required><br>

        <!-- Accessories Dropdown List -->
        <label for="accessory">Accessory:</label>
        <select id="accessory" name="accessory" required>
            <% for (CarAccessories accessory : accessories) { %>
                <option value="<%= accessory.getAccessoryID() %>"><%= accessory.getAccessoryName() %></option>
            <% } %>
        </select><br>

        <!-- Quantity -->
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" min="1" required><br>

        <!-- Payment Status -->
        <label for="paymentStatus">Payment Status:</label>
        <select id="paymentStatus" name="paymentStatus">
            <option value="Processing">Processing</option>
            <option value="Completed">Completed</option>
        </select><br>

        <input type="submit" value="Add">
    </form>

    <a href="home" class="home-btn">Home</a>
</div>
</body>
</html>
