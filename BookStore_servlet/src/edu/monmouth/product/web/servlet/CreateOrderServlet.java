package edu.monmouth.product.web.servlet;

import edu.monmouth.product.domain.Order;
import edu.monmouth.product.domain.OrderItem;
import edu.monmouth.product.domain.Product;
import edu.monmouth.product.domain.User;
import edu.monmouth.product.service.OrderService;
import edu.monmouth.product.util.UUIDUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        try {
            BeanUtils.populate(order,request.getParameterMap());
            order.setId(UUID.randomUUID().toString());
            User user = (User) request.getSession().getAttribute("user");
            order.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        order.setOrdertime(date);
        List<OrderItem> list = new ArrayList<OrderItem>();
        Map<Product,String> cart = (Map<Product, String>) request.getSession().getAttribute("cart");
        for (Product p : cart.keySet()){
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setP(p);
            oi.setBuynum(Integer.parseInt(cart.get(p)));
            list.add(oi);
        }
        order.setOrderItems(list);
        OrderService os = new OrderService();
        os.addOrder(order);
        request.getRequestDispatcher("/pay.jsp").forward(request, response);
    }
}
