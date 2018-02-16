package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.User;
import edu.monmouth.product.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA.UserException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ckcode = request.getParameter("ckcode");
        String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
        if(!checkcode_session.equals(ckcode)){
            request.setAttribute("ckcode_msg","Security code is worry,Please try it again");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        User user = new User();
        user.setRole("common");
        try {
            BeanUtils.populate(user,request.getParameterMap());
            user.setActivecode(UUID.randomUUID().toString());
            UserService us = new UserService();
            us.regist(user);
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("/registersuccess.jsp").forward(request,response);
        }
        catch (edu.monmouth.product.exception.UserException e){
            request.setAttribute("user_msg",e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
}
