package edu.monmouth.product.service;

import edu.monmouth.product.dao.OrderDao;
import edu.monmouth.product.dao.OrderItemDao;
import edu.monmouth.product.dao.ProductDao;
import edu.monmouth.product.domain.Order;
import edu.monmouth.product.util.ManagerThreadLocal;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    OrderItemDao orderItemDao = new OrderItemDao();
    ProductDao productDao = new ProductDao();

    public void addOrder(Order order){
        try {
            ManagerThreadLocal.startTransacation();
            orderDao.addOrder(order);
            orderItemDao.addOrderItem(order);
            productDao.updateProductNum(order);

            ManagerThreadLocal.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            ManagerThreadLocal.rollback();
        }
    }


    public List<Order> findOrderByUserId(int id) throws SQLException {
        return orderDao.findOrders(id);
    }

    public Order findOrdersByOrderId(String orderid) {
        try {
            return orderDao.findOrdersByOrderId(orderid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
