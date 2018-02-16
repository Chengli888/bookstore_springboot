package edu.monmouth.bookstore_springboot.Dao;

import edu.monmouth.bookstore_springboot.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {
    List<Product> findByNameLike(String name);
    List<Product> findByName(String name);
    Page<Product> findByNameLike(String name,Pageable pageable);
    @Transactional
    @Modifying
    @Query("UPDATE Product SET pnum=pnum-?1 WHERE id=?2")
    void updateproductnumber(Integer pnum,Integer id);
    Page<Product> findByCategoryLike(String category,Pageable pageable);
//    @Query("select id,category,description,img_url,name,pnum,price from Product where 1=1 and category like ?1 and name like ?2 and price> ?3 and price< ?4")
//  List<Product> searchProducts( String category,String name,Double minprice,Double maxprice);
}
