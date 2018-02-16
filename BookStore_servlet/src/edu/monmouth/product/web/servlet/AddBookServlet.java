package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.Product;
import edu.monmouth.product.service.ProductService;
import edu.monmouth.product.util.UUIDUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "AddBookServlet")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        try {
            product.setId(UUIDUtil.getUUID());
            BeanUtils.populate(product,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ProductService productService = new ProductService();
        productService.addBook(product);
        request.getRequestDispatcher("booklistservlet").forward(request,response);
    }
}
