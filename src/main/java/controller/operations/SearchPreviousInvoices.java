package controller.operations;

import dao.DAOCarAccessories;
import dao.DAOInvoice;
import entity.Customer;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "search-previous-invoices", value = "/search-previous-invoices")
public class SearchPreviousInvoices extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
        } else {
            String search = request.getParameter("search");
            if (search == null || search.equals("")) {
                if (request.getSession().getAttribute("accountType").equals("customer"))
                    request.setAttribute("list", new DAOInvoice().searchInvoicesByCustomer(((Customer) request.getSession().getAttribute("customer"))));
                else if (request.getSession().getAttribute("accountType").equals("manager") || request.getSession().getAttribute("accountType").equals("accountant"))
                    request.setAttribute("list", new DAOInvoice().getAllInvoices());
            } else {
                if (request.getSession().getAttribute("accountType").equals("customer"))
                    request.setAttribute("list", new DAOInvoice().searchInvoicesByCustomerIDAndKeyWord(((Customer) request.getSession().getAttribute("customer")), search));
                else if (request.getSession().getAttribute("accountType").equals("manager") || request.getSession().getAttribute("accountType").equals("accountant"))
                    request.setAttribute("list", new DAOInvoice().searchInvoices(search));
            }
            request.getRequestDispatcher("search-previous-invoices.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }
}
