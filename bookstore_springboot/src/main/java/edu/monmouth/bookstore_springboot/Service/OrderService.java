package edu.monmouth.bookstore_springboot.Service;

import edu.monmouth.bookstore_springboot.Dao.ProductDao;
import edu.monmouth.bookstore_springboot.domain.OrderItem;
import edu.monmouth.bookstore_springboot.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    private ProductDao productDao;

    public void updateProductNum(Orders orders) {
       Set<OrderItem> orderItemsset = orders.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<OrderItem>(orderItemsset);
        for(int i = 0;i<orderItems.size();i++){
            productDao.updateproductnumber(orderItems.get(i).getBuynum(),orderItems.get(i).getP().getId());
        }
    }
}