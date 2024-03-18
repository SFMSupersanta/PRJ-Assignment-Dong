<%@ page import="entity.CarAccessories" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Car Accessory</title>
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
    <h1>Edit Car Accessory</h1>
    <%
        CarAccessories accessory = (CarAccessories) request.getAttribute("carAccessory");
    %>

    <form action="control-car-accessories" method="get">
        <input type="hidden" name="id" value="<%=accessory.getAccessoryID()%>">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="commit" value="edit">
        <label for="accessoryName">Accessory Name:</label>
        <input type="text" id="accessoryName" name="accessoryName" value="<%=accessory.getAccessoryName()%>"><br>
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="<%=accessory.getPrice()%>"><br>
        <label for="stockQuantity">Stock Quantity:</label>
        <input type="text" id="stockQuantity" name="stockQuantity" value="<%=accessory.getStockQuantity()%>"><br>
        <input type="submit" value="Save">
    </form>

    <a href="home" class="home-btn">Home</a>
</div>
</body>
</html>
