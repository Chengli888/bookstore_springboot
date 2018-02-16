package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.PageBean;
import edu.monmouth.product.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PageServlet")
public class PageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        if(category==null){
            category="";
        }
        int  currentpage = 1;
        int  pagesize = 4;
        String currpage = request.getParameter("currentPage");
        if(currpage!=null){
            currentpage = Integer.parseInt(currpage);
        }
        ProductService productService = new ProductService();
        try {
            PageBean pb = productService.findProductsPage(currentpage,pagesize,category);
            request.getSession().setAttribute("pb",pb);
            request.getRequestDispatcher("/product_list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
