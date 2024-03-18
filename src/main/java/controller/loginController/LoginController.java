package controller.loginController;

import dao.DAOAccount;
import dao.DAOCustomer;
import entity.Account;
import entity.Customer;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account;
        // Validate credentials (you might want to fetch these from a database)
        if ((account = new DAOAccount().login(username, password)) != null){
            // If credentials are valid, create a session
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            session.setAttribute("username", username);
            session.setAttribute("balance", account.getAccountBalance());
            session.setAttribute("accountType", account.getAccountType());
            Customer customer = new DAOCustomer().getCustomerByID(account.getCustomerID());
            session.setAttribute("customer", customer);
            session.setAttribute("vehicleName", customer.getVehicleName());
            session.setAttribute("licencePlate", customer.getLicencePlate());
            // Redirect to a success page or some other page
            response.sendRedirect("home");
        } else {
            // If credentials are invalid, redirect back to the login page with an error message
            response.sendRedirect("login.jsp?error=1");
        }
    }

    public void destroy() {
    }
}