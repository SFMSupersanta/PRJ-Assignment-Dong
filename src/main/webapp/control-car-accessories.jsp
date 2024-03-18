<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.CarAccessories" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Accessories List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        input[type="text"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .home-btn, .add-accessory-btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s ease;
            margin-bottom: 20px;
        }
        .home-btn:hover, .add-accessory-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<%
    ArrayList<CarAccessories> accessories = (ArrayList<CarAccessories>) request.getAttribute("list");
%>
<div class="container">
    <h1>Car Accessories List</h1>
    <!-- Add Accessory button -->
    <a href="control-car-accessories?action=insert" class="add-accessory-btn">Add Accessory</a>

    <!-- Search bar -->
    <form action="control-car-accessories" method="get">
        <input type="text" name="search" placeholder="Search car accessories...">
        <input type="submit" value="Search">
    </form>

    <!-- Accessories table -->
    <table>
        <thead>
        <tr>
            <th>Accessory ID</th>
            <th>Accessory Name</th>
            <th>Price</th>
            <th>Stock Quantity</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <% for (CarAccessories accessory : accessories) { %>
        <tr>
            <td><c:out value="<%= accessory.getAccessoryID() %>" /></td>
            <td><c:out value="<%= accessory.getAccessoryName() %>" /></td>
            <td><c:out value="<%= accessory.getPrice() %>" /></td>
            <td><c:out value="<%= accessory.getStockQuantity() %>" /></td>
            <td><a href="control-car-accessories?id=<%= accessory.getAccessoryID() %>&action=edit">Edit</a></td>
            <td><a href="control-car-accessories?id=<%= accessory.getAccessoryID() %>&action=delete">Delete</a></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <!-- Home button -->
    <a href="home" class="home-btn">Home</a>
</div>
</body>
</html>
