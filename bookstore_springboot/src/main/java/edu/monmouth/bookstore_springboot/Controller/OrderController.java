package edu.monmouth.bookstore_springboot.Controller;

import edu.monmouth.bookstore_springboot.Dao.OrderDao;
import edu.monmouth.bookstore_springboot.Dao.OrderitemsDao;
import edu.monmouth.bookstore_springboot.Service.OrderService;
import edu.monmouth.bookstore_springboot.Service.UUIDUtil;
import edu.monmouth.bookstore_springboot.domain.OrderItem;
import edu.monmouth.bookstore_springboot.domain.Orders;
import edu.monmouth.bookstore_springboot.domain.Product;
import edu.monmouth.bookstore_springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class OrderController {
    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderitemsDao orderitemsDao;
    @Autowired
    private OrderService orderService;
@RequestMapping("order")
    public String OrderView(){
    return "order";
}
 @RequestMapping("createOrder.do")
    public String CreateOrder(Orders orders, HttpSession session){
     User user = (User)session.getAttribute("user");
     orders.setUser(user);
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
     String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
     orders.setOrdertime(date);
     Set<OrderItem> list = new HashSet<OrderItem>();
     Map<Product,String> cart = (Map<Product, String>) session.getAttribute("cart");
     for (Product p : cart.keySet()){
         OrderItem oi = new OrderItem();
         oi.setOrders(orders);
         oi.setP(p);
         oi.setBuynum(Integer.parseInt(cart.get(p)));
         list.add(oi);
     }
     orders.setOrderItems(list);
     session.setAttribute("orders",orders);
     session.setAttribute("count",orders.getMoney());
     return "pay";

 }
 @RequestMapping("pay.do")
    public String pay(HttpSession session){
        Orders orders = (Orders) session.getAttribute("orders");
        orders.setPaystate(1);
        orderDao.save(orders);
        orderService.updateProductNum(orders);

        return "index";
 }
    @RequestMapping("findOrderByUserId.do")
    public String findOrderByUserId(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Orders> orderlist = orderDao.findByUser(user);
        System.out.println(orderlist.size()+"------------------orderlist sizes");
        session.setAttribute("orders",orderlist);
        return "orderlist";
    }
    @RequestMapping("findOrderItemsByOrderId")
    public String findOrderItemsByOrderId(@RequestParam("orderid") String id,HttpSession session){
        Orders orders = orderDao.findOne(Integer.valueOf(id));
        Set<OrderItem> orderItemList = orderitemsDao.findByOrders(orders);
        orders.setOrderItems(orderItemList);
        session.setAttribute("order",orders);
        return "orderInfo";
    }
    @RequestMapping("deleteOrderItemsByOrderId")
    public String deleteOrderItemsByOrderId(@RequestParam("orderid") String id,HttpSession session){
        Orders orders = orderDao.findOne(Integer.valueOf(id));
        orderitemsDao.deleteByOrders(orders);
        orderDao.delete(orders.getId());
        User user = (User) session.getAttribute("user");
        List<Orders> orderlist = orderDao.findByUser(user);
        session.setAttribute("orders",orderlist);
        return "orderlist";
    }
}
