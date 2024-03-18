package controller.manager;

import dao.DAOInvoice;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "generate-revenue-report", value = "/generate-revenue-report")
public class GenerateRevenueReport extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().getAttribute("account") == null){
            response.sendRedirect("login.jsp");
        } else {
            if(request.getParameter("action") == null){
                response.sendRedirect("generate-revenue-report.jsp");
            }
            else {
                int year = Integer.parseInt(request.getParameter("year"));
                int month = Integer.parseInt(request.getParameter("month"));
                request.setAttribute("revenue", new DAOInvoice().generateRevenueReport(year, month));
                request.getRequestDispatcher("generate-revenue-report.jsp").forward(request, response);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }
}
