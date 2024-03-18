<%@ page import="entity.Invoice" %>
<%@ page import="dao.DAOCustomer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Invoice</title>
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
        .add-invoice-btn {
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
        .add-invoice-btn:hover {
            background-color: #218838; /* Darker green color */
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Edit Invoice</h1>
    <%
        Invoice invoice = (Invoice) request.getAttribute("invoice");
    %>

    <form action="control-invoice-information" method="get">
        <input type="hidden" name="id" value="<%=invoice.getInvoiceID()%>">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="commit" value="edit">
        <input type="hidden" name="CustomerID" value="<%=invoice.getCustomerID()%>">
        <label for="customerName">Customer Name:</label>
        <input type="text" id="customerName" name="customerName" value="<%= (new DAOCustomer().getCustomerByID(invoice.getCustomerID())).getCustomerName() %>" disabled><br>
        <label for="invoiceDate">Invoice Date:</label>
        <input type="text" id="invoiceDate" name="date" value="<%=invoice.getInvoiceDate()%>"><br>
        <label for="totalAmount">Total Amount:</label>
        <input type="text" id="totalAmount" name="totalAmount" value="<%=invoice.getTotalAmount()%>"><br>
        <label for="paymentStatus">Payment Status:</label>
        <select id="paymentStatus" name="paymentStatus">
            <option value="Processing" selected>Processing</option>
            <option value="Completed" >Completed</option>
        </select><br>
        <input type="submit" value="Save">
    </form>

    <a href="home">Home</a>
</div>
</body>
</html>
