<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Invoice" %>
<%@ page import="dao.DAOCustomer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Invoices List</title>
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
        input[type="text"] {
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
        .home-btn {
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
        }
        .home-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<%
    ArrayList<Invoice> invoices = (ArrayList<Invoice>) request.getAttribute("list");
%>
<div class="container">
    <h1>Invoices List</h1>

    <!-- Search bar -->
    <form action="search-previous-invoices" method="get">
        <input type="text" name="search" placeholder="Search invoices...">
        <input type="submit" value="Search">
    </form>

    <!-- Invoices table -->
    <table>
        <thead>
        <tr>
            <th>Invoice ID</th>
            <th>Customer Name</th>
            <th>Invoice Date</th>
            <th>Total Amount</th>
            <th>Payment Status</th>
        </tr>
        </thead>
        <tbody>
        <% for (Invoice invoice : invoices) { %>
        <tr>
            <td><c:out value="<%= invoice.getInvoiceID() %>" /></td>
            <td><c:out value="<%= (new DAOCustomer().getCustomerByID(invoice.getCustomerID())).getCustomerName() %>" /></td>
            <td><c:out value="<%= invoice.getInvoiceDate() %>" /></td>
            <td><c:out value="<%= invoice.getTotalAmount() %>" /></td>
            <td><c:out value="<%= invoice.getPaymentStatus() %>" /></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <!-- Home button -->
    <a href="home" class="home-btn">Home</a>
</div>
</body>
</html>
