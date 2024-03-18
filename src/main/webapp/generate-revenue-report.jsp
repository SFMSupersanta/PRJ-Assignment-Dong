<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generate Revenue Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .button-container {
            text-align: center;
        }
        .generate-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .generate-btn:hover {
            background-color: #0056b3;
        }
        .filter-menu {
            display: inline-block;
            padding: 10px 20px;
            background-color: #f8f9fa;
            color: #333;
            border: 1px solid #ccc;
            border-radius: 3px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .filter-menu:hover {
            background-color: #e9ecef;
        }
        .revenue-amount {
            margin-top: 20px;
            font-size: 24px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Generate Revenue Report</h1>
    <div class="button-container">
        <button class="generate-btn" onclick="toggleFilter()">Generate Revenue Report This Month</button>
        <div id="filter" style="display: none;">
            <form action="generate-revenue-report" method="get">
                <input type="hidden" name="action" value="generate">
                <label for="year">Select Year:</label>
                <select id="year" name="year">
                    <!-- 1980-2024 -->
                    <option value="1980">1980</option>
                    <option value="1981">1981</option>
                    <option value="1982">1982</option>
                    <option value="1983">1983</option>
                    <option value="1984">1984</option>
                    <option value="1985">1985</option>
                    <option value="1986">1986</option>
                    <option value="1987">1987</option>
                    <option value="1988">1988</option>
                    <option value="1989">1989</option>
                    <option value="1990">1990</option>
                    <option value="1991">1991</option>
                    <option value="1992">1992</option>
                    <option value="1993">1993</option>
                    <option value="1994">1994</option>
                    <option value="1995">1995</option>
                    <option value="1996">1996</option>
                    <option value="1997">1997</option>
                    <option value="1998">1998</option>
                    <option value="1999">1999</option>
                    <option value="2000">2000</option>
                    <option value="2001">2001</option>
                    <option value="2002">2002</option>
                    <option value="2003">2003</option>
                    <option value="2004">2004</option>
                    <option value="2005">2005</option>
                    <option value="2006">2006</option>
                    <option value="2007">2007</option>
                    <option value="2008">2008</option>
                    <option value="2009">2009</option>
                    <option value="2010">2010</option>
                    <option value="2011">2011</option>
                    <option value="2012">2012</option>
                    <option value="2013">2013</option>
                    <option value="2014">2014</option>
                    <option value="2015">2015</option>
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>

                </select><br>
                <label for="month">Select Month:</label>
                <select id="month" name="month">
                    <option value="1">January</option>
                    <option value="2">February</option>
                    <option value="3">March</option>
                    <option value="4">April</option>
                    <option value="5">May</option>
                    <option value="6">June</option>
                    <option value="7">July</option>
                    <option value="8">August</option>
                    <option value="9">September</option>
                    <option value="10">October</option>
                    <option value="11">November</option>
                    <option value="12">December</option>
                </select><br>
                <input type="submit" value="Generate Report">
            </form>
        </div>
    </div>
    <div class="revenue-amount">
        <!-- Placeholder value for the revenue amount -->
        Total Revenue: $<%=(Double)request.getAttribute("revenue")%> <!-- Replace this with the actual revenue amount -->

    </div>
</div>

<script>
    function toggleFilter() {
        var filter = document.getElementById("filter");
        if (filter.style.display === "none") {
            filter.style.display = "block";
        } else {
            filter.style.display = "none";
        }
    }
</script>
</body>
</html>
