package edu.monmouth.bookstore_springboot.Dao;

import edu.monmouth.bookstore_springboot.domain.Orders;
import edu.monmouth.bookstore_springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Orders,Integer> {
    List<Orders> findByUser(User user);
}
