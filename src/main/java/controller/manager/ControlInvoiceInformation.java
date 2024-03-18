package controller.manager;

import dao.DAOInvoice;
import entity.Invoice;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "control-invoice-information", value = "/control-invoice-information")
public class ControlInvoiceInformation extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
        } else {
            String search = request.getParameter("search");
            if (search == null || search.equals("")) {
                request.setAttribute("list", new DAOInvoice().getAllInvoices());
            } else {
                request.setAttribute("list", new DAOInvoice().searchInvoices(search));
            }
            if (request.getParameter("action") == null)
                request.getRequestDispatcher("control-invoice-information.jsp").forward(request, response);

            String action = request.getParameter("action");
            if (action != null && action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                new DAOInvoice().deleteInvoice(id);
                response.sendRedirect("control-invoice-information");
            }
            if (action != null && action.equals("edit")) {
                if (request.getParameter("commit") == null) {
                    Invoice invoice = new DAOInvoice().getInvoice(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("invoice", invoice);
                    request.getRequestDispatcher("update-invoice.jsp").forward(request, response);
                } else {
                    int invoiceID = Integer.parseInt(request.getParameter("id"));
                    int customerID = Integer.parseInt(request.getParameter("CustomerID"));
                    String date = request.getParameter("date");
                    double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
                    String paymentStatus = request.getParameter("paymentStatus");
                    new DAOInvoice().updateInvoice(new Invoice(invoiceID, customerID, date, totalAmount, paymentStatus));
                    response.sendRedirect("control-invoice-information");
                }
            }
            if (action != null && action.equals("insert")) {
                if (request.getParameter("commit") == null) {
                    response.sendRedirect("generate-invoices");
                }
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }
}
