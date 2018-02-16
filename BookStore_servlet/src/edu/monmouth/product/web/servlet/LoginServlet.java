package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.User;
import edu.monmouth.product.exception.UserException;
import edu.monmouth.product.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService us = new UserService();
        String path = "/index.jsp";
        try {
            User user = us.login(username,password);
            if("admin".equals(user.getRole())){
                path="/admin/login/home.jsp";
            }
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher(path).forward(request,response);
        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("user_msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
