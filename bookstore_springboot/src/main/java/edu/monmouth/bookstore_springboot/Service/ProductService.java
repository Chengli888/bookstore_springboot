package edu.monmouth.bookstore_springboot.Service;

import edu.monmouth.bookstore_springboot.Dao.ProductDao;
import edu.monmouth.bookstore_springboot.domain.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {
    @Resource
    private ProductDao productDao;
    public String findProductName(String name) {
        List<Product> productList = productDao.findByNameLike(name);
        String res="";
        for(int i=0;i<productList.size();i++){
            if(i>0){
                res+=","+productList.get(i).getName();
            }else {
                res+=productList.get(i).getName();
            }
        }
        return res;
    }
}
