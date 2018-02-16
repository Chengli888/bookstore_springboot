package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.User;
import edu.monmouth.product.service.ProductService;
import edu.monmouth.product.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FindUserByIdServlet")
public class FindUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserService userService = new UserService();
        try {
            User user = userService.findUserById(id);
            request.setAttribute("u",user);
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request,response);
        } catch (SQLException e) {

            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }
}
