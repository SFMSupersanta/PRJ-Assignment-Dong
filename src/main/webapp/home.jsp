<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
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
        h1, h2 {
            color: #333;
        }
        button {
            display: block;
            width: 100%;
            margin-top: 15px;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>


<body>
<%
    String accountType = (String) request.getSession().getAttribute("accountType");
    String username = (String) request.getSession().getAttribute("username");
    Double balance = (Double) request.getSession().getAttribute("balance");
%>
<div class="container">

    <h1>Home</h1>
    <h2>Account Type: <c:out value="${accountType}" /></h2>
    <h2>Username: <c:out value="${username}" /></h2>
    <h2>Balance: <c:out value="${balance}" /></h2>

    <c:choose>
        <c:when test="${accountType eq 'customer'}">
            <button onclick="location.href='search-accessories-price'">Search Car Accessories Price</button>
            <button onclick="location.href='search-previous-invoices'">Search and Check Previous Invoices</button>
        </c:when>
        <c:when test="${accountType eq 'accountant'}">
            <button onclick="location.href='search-accessories-price'">Search Car Accessories Price</button>
            <button onclick="location.href='search-previous-invoices'">Search and Check Previous Invoices</button>
            <button onclick="location.href='generate-invoice'">Generate Invoice</button>
        </c:when>
        <c:when test="${accountType eq 'manager'}">
            <button onclick="location.href='search-accessories-price'">Search Car Accessories Price</button>
            <button onclick="location.href='search-previous-invoices'">Search and Check Previous Invoices</button>
            <button onclick="location.href='control-invoice-information'">Control Invoice Information</button>
            <button onclick="location.href='control-car-accessories'">Control Car Accessories</button>
            <button onclick="location.href='control-account-of-customers'">Control Account of Customers</button>
            <button onclick="location.href='generate-revenue-report'">Generate Revenue Report</button>
        </c:when>
    </c:choose>
</div>
</body>
</html>
