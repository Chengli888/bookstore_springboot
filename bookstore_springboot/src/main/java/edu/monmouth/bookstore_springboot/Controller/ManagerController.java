package edu.monmouth.bookstore_springboot.Controller;

import edu.monmouth.bookstore_springboot.Dao.ProductDao;
import edu.monmouth.bookstore_springboot.domain.Product;
import edu.monmouth.bookstore_springboot.domain.Product_show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ManagerController {
    @Resource
    private ProductDao productDao;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    @RequestMapping("productlist")
    public String WelcomeView(HttpSession session){
        List<Product> productList = productDao.findAll();
        session.setAttribute("products",productList);
        return "redirect:/admin/products/list.jsp";
    }
    @RequestMapping("searchProducts.do")
    public String searchProducts(@RequestParam("id") String id,@RequestParam("category") String category,
                                 @RequestParam("name") String name,@RequestParam("minprice") Double minprice,
                                 @RequestParam("maxprice") Double maxprice,HttpSession session){

        String sql = "select p.id,p.name,p.price,p.pnum,p.category,p.description,p.img_url from Product p where 1=1";

        if(!"".equals(id.trim())&&!(id==null)){
            sql+=" and id like '"+id+"' ";
        }
        if(!"".equals(category.trim())&&!(category==null)){
            sql+=" and category like '"+category+"'";
        }
        if(!"".equals(name.trim())&&!(name==null)){
            sql+=" and name like '"+name+"'";
        }
        if(!"".equals(minprice)&&minprice!=null){
            sql+=" and price>'"+minprice+"' ";
        }
        if(!"".equals(maxprice)&&maxprice!=null){
            sql+=" and price< '"+maxprice+"' ";
        }
        Query query = entityManager.createNativeQuery(sql,Product.class);
        System.out.println(sql);

        List<Product> productList = query.getResultList();
        session.setAttribute("products",productList);
        System.out.println(productList.size()+"--------------------------serch"+category+name+maxprice+maxprice);
        return "redirect:/admin/products/list.jsp";
    }
    @RequestMapping("add.do")
    public String addproduct(Product product,HttpSession session){
        productDao.save(product);
        List<Product> productList = productDao.findAll();
        session.setAttribute("products",productList);
        return "redirect:/admin/products/list.jsp";
    }
    @RequestMapping("edit.do")
    public String edit(@RequestParam("id") Integer id,HttpSession session){
        Product product = productDao.findOne(id);
        session.setAttribute("product",product);
        return "redirect:/admin/products/edit.jsp";
    }
    @RequestMapping("updateproduct.do")
    public String updateproduct(Product product,HttpSession session){
        Product product1 = (Product) session.getAttribute("product");
        productDao.delete(product1.getId());
        productDao.save(product);
        List<Product> productList = productDao.findAll();
        session.setAttribute("products",productList);
        return "redirect:/admin/products/list.jsp";
    }
    @RequestMapping("deleteproduct.do")
    public String deleteproduct(@RequestParam("id") Integer id,HttpSession session){
       productDao.delete(id);
        List<Product> productList = productDao.findAll();
        session.setAttribute("products",productList);
        return "redirect:/admin/products/list.jsp";
    }
    @RequestMapping("deleteproducts.do")
    public  String deleteproducts(HttpServletRequest request)
    {
        String[] ids = request.getParameterValues("ids");
        for (String id:ids){
            productDao.delete(Integer.parseInt(id));
        }
       return  "redirect:productlist";
    }
}
