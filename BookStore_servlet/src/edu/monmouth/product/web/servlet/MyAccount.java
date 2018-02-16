package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyAccount")
public class MyAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }else {
            String path = "myAccount.jsp";
            if("admin".equals(user.getRole())){
                path = "/admin/login/home.jsp";
            }
            request.getRequestDispatcher(path).forward(request,response);
        }
    }
}
