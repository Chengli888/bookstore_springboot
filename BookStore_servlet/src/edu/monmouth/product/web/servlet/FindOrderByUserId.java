package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.Order;
import edu.monmouth.product.domain.User;
import edu.monmouth.product.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FindOrderByUserId")
public class FindOrderByUserId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        OrderService os = new OrderService();
        List<Order> orders = null;
        try {
            orders = os.findOrderByUserId(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("orders", orders);
        request.setAttribute("count", orders.size());
        request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
    }
}
