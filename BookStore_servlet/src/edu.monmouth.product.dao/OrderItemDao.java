package edu.monmouth.product.dao;

import edu.monmouth.product.domain.Order;
import edu.monmouth.product.domain.OrderItem;
import edu.monmouth.product.util.C3P0Util;
import edu.monmouth.product.util.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDao {
    public void addOrderItem(Order order) throws SQLException {
        List<OrderItem> orderItems = order.getOrderItems();
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        Object[][] params = new Object[orderItems.size()][];

        for(int i = 0;i<params.length;i++){
            params[i] = new Object[]{order.getId(),orderItems.get(i).getP().getId(),orderItems.get(i).getBuynum()};
        }
        queryRunner.batch("INSERT INTO orderitem VALUES(?,?,?)",params);
    }
}
