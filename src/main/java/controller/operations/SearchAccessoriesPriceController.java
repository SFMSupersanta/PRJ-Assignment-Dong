package controller.operations;

import dao.DAOCarAccessories;
import entity.CarAccessories;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "search-accessories-price", value = "/search-accessories-price")
public class SearchAccessoriesPriceController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().getAttribute("account") == null){
            response.sendRedirect("login.jsp");
        } else {
            String search = request.getParameter("search");
            if(search == null || search.equals("")) {
                request.setAttribute("list", new DAOCarAccessories().getAllCarAccessories());
            } else {
                request.setAttribute("list", new DAOCarAccessories().searchByName(search));
            }
            request.getRequestDispatcher("search-accessories-price.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }
}
