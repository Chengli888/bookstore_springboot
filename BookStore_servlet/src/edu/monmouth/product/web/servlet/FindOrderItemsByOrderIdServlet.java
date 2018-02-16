package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.Order;
import edu.monmouth.product.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FindOrderItemsByOrderIdServlet")
public class FindOrderItemsByOrderIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid = request.getParameter("orderid");

        OrderService os = new OrderService();
        Order order = os.findOrdersByOrderId(orderid);

        request.setAttribute("order", order);
        request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
    }
}
