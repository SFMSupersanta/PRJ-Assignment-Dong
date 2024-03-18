package controller.accountant;

import dao.DAOAccount;
import dao.DAOCarAccessories;
import dao.DAOCustomer;
import dao.DAOInvoice;
import entity.Invoice;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "generate-invoice", value = "/generate-invoice")
public class GenerateInvoices extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().getAttribute("account") == null || request.getSession().getAttribute("account").equals("customer")){
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("customers", new DAOCustomer().getAllCustomer());
            request.setAttribute("accessories", new DAOCarAccessories().getAllCarAccessories());
            request.getRequestDispatcher("insert-invoice.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("account") == null || request.getSession().getAttribute("account").equals("customer")){
            response.sendRedirect("login.jsp");
        } else {
            //<form action="generate-invoice" method="post">
            //        <label for="customerId">Customer ID:</label>
            //        <input type="text" id="customerId" name="customerId" required><br>
            //        <label for="invoiceDate">Invoice Date:</label>
            //        <input type="text" id="invoiceDate" name="invoiceDate" placeholder="YYYY-MM-DD" required><br>
            //        <%
            //            ArrayList<CarAccessories> accessories = (ArrayList<CarAccessories>) request.getAttribute("accessories");
            //        %>
            //        <!-- Accessories Dropdown List -->
            //        <label for="accessory">Accessory:</label>
            //        <select id="accessory" name="accessory" required>
            //            <% for (CarAccessories accessory : accessories) { %>
            //                <option value="<%= accessory.getAccessoryID() %>"><%= accessory.getAccessoryName() %></option>
            //            <% } %>
            //        </select><br>
            //
            //        <!-- Quantity -->
            //        <label for="quantity">Quantity:</label>
            //        <input type="number" id="quantity" name="quantity" min="1" required><br>
            //
            //        <!-- Payment Status -->
            //        <label for="paymentStatus">Payment Status:</label>
            //        <select id="paymentStatus" name="paymentStatus">
            //            <option value="Processing">Processing</option>
            //            <option value="Completed">Completed</option>
            //        </select><br>
            //
            //        <input type="submit" value="Add">
            //    </form>
            int customerID = Integer.parseInt(request.getParameter("customerId"));
            String date = request.getParameter("invoiceDate");
            int accessoryID = Integer.parseInt(request.getParameter("accessory"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String paymentStatus = request.getParameter("paymentStatus");

            // Create invoice
            Invoice invoice = new Invoice(customerID, date, (new DAOCarAccessories().getCarAccessories(accessoryID)).getPrice() * quantity, paymentStatus);
            new DAOInvoice().addInvoice(invoice);

            if(request.getSession().getAttribute("accountType").equals("accountant"))
                response.sendRedirect("generate-invoice");
            else
                response.sendRedirect("control-invoice-information");
        }
    }

    public void destroy() {
    }
}
