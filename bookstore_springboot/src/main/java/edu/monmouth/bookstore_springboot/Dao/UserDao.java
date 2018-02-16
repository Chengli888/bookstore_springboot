package edu.monmouth.bookstore_springboot.Dao;

import edu.monmouth.bookstore_springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

  User findByUsernameAndPassword(String username,String password);
  @Transactional
  @Modifying
  @Query("update User set password=?1,gender=?2,telephone=?3 WHERE id=?4")
  void updateUser(String password,String gender,String telephone,Integer id);
}
