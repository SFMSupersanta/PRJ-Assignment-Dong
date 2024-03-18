package controller.manager;

import dao.DAOCarAccessories;
import dao.DAOInvoice;
import entity.CarAccessories;
import entity.Invoice;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "control-car-accessories", value = "/control-car-accessories")
public class ControlCarAccessories extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
        } else {
            String search = request.getParameter("search");
            if (search == null || search.equals("")) {
                request.setAttribute("list", new DAOCarAccessories().getAllCarAccessories());
            } else {
                request.setAttribute("list", new DAOCarAccessories().searchByName(search));
            }
            if (request.getParameter("action") == null)
                request.getRequestDispatcher("control-car-accessories.jsp").forward(request, response);

            String action = request.getParameter("action");
            if (action != null && action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                new DAOCarAccessories().deleteCarAccessories(id);
                response.sendRedirect("control-car-accessories");
            }
            if (action != null && action.equals("edit")) {
                if (request.getParameter("commit") == null) {
                    CarAccessories carAccessory = new DAOCarAccessories().getCarAccessories(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("carAccessory", carAccessory);
                    request.getRequestDispatcher("update-car-accessory.jsp").forward(request, response);
                } else {
                    int invoiceID = Integer.parseInt(request.getParameter("id"));
                    String accessoryName = request.getParameter("accessoryName");
                    String price = request.getParameter("price");
                    int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
                    new DAOCarAccessories().updateCarAccessories(new CarAccessories(invoiceID, accessoryName, Double.parseDouble(price), stockQuantity));
                    response.sendRedirect("control-car-accessories");
                }
            }
            if (action != null && action.equals("insert")) {
                request.getRequestDispatcher("insert-car-accessory.jsp").forward(request, response);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //<form action="control-car-accessories" method="post">
        //        <label for="accessoryName">Accessory Name:</label>
        //        <input type="text" id="accessoryName" name="accessoryName" required><br>
        //        <label for="price">Price:</label>
        //        <input type="text" id="price" name="price" required><br>
        //        <label for="stockQuantity">Stock Quantity:</label>
        //        <input type="text" id="stockQuantity" name="stockQuantity" required><br>
        //        <input type="submit" value="Add">
        //    </form>
        String accessoryName = request.getParameter("accessoryName");
        String price = request.getParameter("price");
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        new DAOCarAccessories().addCarAccessories(new CarAccessories(accessoryName, Double.parseDouble(price), stockQuantity));
        response.sendRedirect("control-car-accessories");
    }

    public void destroy() {
    }
}
