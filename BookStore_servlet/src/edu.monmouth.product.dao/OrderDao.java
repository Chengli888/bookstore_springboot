package edu.monmouth.product.dao;

import edu.monmouth.product.domain.Order;
import edu.monmouth.product.domain.OrderItem;
import edu.monmouth.product.domain.Product;
import edu.monmouth.product.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao {
    public void addOrder(Order order) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        queryRunner.update("insert into orders values(?,?,?,?,?,?,?,?)",order.getId(),order.getMoney()
        ,order.getReceiverAddress(),order.getReceiverName(),order.getReceiverPhone(),order.getPaystate(),order.getOrdertime(),
                order.getUser().getId());

    }

    public List<Order> findOrders(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        return queryRunner.query("select * from orders where user_id=?",new BeanListHandler<Order>(Order.class),id);
    }

    public Order findOrdersByOrderId(String orderid) throws SQLException{
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        //得到一个定单
        Order order = qr.query("select * from orders where id=?", new BeanHandler<Order>(Order.class),orderid);
        //得到当前定单中的所有定单项
        //List<OrderItem> orderItems = qr.query("select * from orderItem where order_id=? ", new  BeanListHandler<OrderItem>(OrderItem.class),orderid);
        //得到所有定单项中的商品信息
        //List<Product> products = qr.query("select * from products where id in(select product_id from orderitem where order_id=?)", new BeanListHandler<Product>(Product.class),orderid);

        List<OrderItem>  orderItems = qr.query("SELECT * FROM orderitem o,products p WHERE p.id=o.product_id AND order_id=?", new ResultSetHandler<List<OrderItem>>(){

            public List<OrderItem> handle(ResultSet rs) throws SQLException {
                List<OrderItem> orderItems = new ArrayList<OrderItem>();
                while(rs.next()){

                    //封装OrderItem对象
                    OrderItem oi = new OrderItem();
                    oi.setBuynum(rs.getInt("buynum"));
                    //封装Product对象
                    Product p = new Product();
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getDouble("price"));
                    //把每个p对象封装到OrderItem对象中
                    oi.setP(p);
                    //把每个OrderItem对象封装到集合中
                    orderItems.add(oi);
                }
                return orderItems;
            }

        },orderid);

        //把所有的定单项orderItems添加到主单对象Order中
        order.setOrderItems(orderItems);

        return order;
    }
}
