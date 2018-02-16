package edu.monmouth.bookstore_springboot.Dao;

import edu.monmouth.bookstore_springboot.domain.OrderItem;
import edu.monmouth.bookstore_springboot.domain.Orders;
import edu.monmouth.bookstore_springboot.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface OrderitemsDao extends JpaRepository<OrderItem,Integer> {


   Set<OrderItem> findByOrders(Orders orders);
    @Transactional
    @Modifying
   void deleteByOrders(Orders orders);
}
