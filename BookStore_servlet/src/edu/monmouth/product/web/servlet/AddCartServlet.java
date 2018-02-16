package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.Product;
import edu.monmouth.product.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        ProductService ps = new ProductService();
        Product b = ps.findBookById(id);
        HttpSession session = request.getSession();
        Map<Product,String> cart = (Map<Product,String>)session.getAttribute("cart");
        int num = 1;
        if(cart==null){
            cart =new HashMap<Product,String>();
        }
        if(cart.containsKey(b)){
            num=Integer.parseInt(cart.get(b))+1;
        }
        cart.put(b,num+"");
        session.setAttribute("cart",cart);
        //out.print("<a href='"+request.getContextPath()+"/pageServlet'>继续购物</a>,<a href='"+request.getContextPath()+"/cart.jsp'>查看购物车</a>");
        out.print("<a href='"+request.getContextPath()+"/PageServlet'>countine shopping</a>,<a href='"+request.getContextPath()+"/cart.jsp'>watch cart</a>");
    }
}
