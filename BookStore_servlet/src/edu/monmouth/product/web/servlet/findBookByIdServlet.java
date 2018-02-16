package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.Product;
import edu.monmouth.product.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "findBookByIdServlet")
public class findBookByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService  = new ProductService();
        Product product = productService.findBookById(request.getParameter("id"));
          request.setAttribute("product",product);
        request.getRequestDispatcher("/admin/products/edit.jsp").forward(request,response);
    }
}
