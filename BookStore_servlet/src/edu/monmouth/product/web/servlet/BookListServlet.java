package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.Product;
import edu.monmouth.product.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet")
public class BookListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Product> productslist = ps.findallproducts();
        if (productslist !=null){
            request.setAttribute("products",productslist);
            request.getRequestDispatcher("/admin/products/list.jsp").forward(request,response);
    }
}
}
