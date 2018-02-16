package edu.monmouth.bookstore_springboot.Controller;

import edu.monmouth.bookstore_springboot.Dao.ProductDao;
import edu.monmouth.bookstore_springboot.Service.ProductService;
import edu.monmouth.bookstore_springboot.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class ProductController {
    @Resource
    private ProductService productService;
    @Autowired
    private ProductDao productDao;
    @ResponseBody
    @RequestMapping("findProductName.do")
    public String findProductName(String name){
        String res = "";
        res = productService.findProductName("%"+name+"%");
       return res;
    }
    @RequestMapping("pagelist.do")
    public String Search(@RequestParam("currentPage") String currentPage, HttpSession session){
        if(currentPage!=null) {
            int pageCurrent = Integer.parseInt(currentPage);
            Pageable pageable = new PageRequest(pageCurrent-1,2,Sort.Direction.DESC,"id");
            String name ="%"+session.getAttribute("product_name")+"%";
            Page<Product> page = productDao.findByNameLike(name,pageable);
            List<Product> productList = page.getContent();
            System.out.println(productList.size());
            session.setAttribute("pd",productList);
            session.setAttribute("totalPage",page.getTotalPages());
            session.setAttribute("currentPage",pageCurrent);
        }
        System.out.println(currentPage+"--------------current");
        return "product_list";
    }
    @RequestMapping("page.do")
    public String Page(HttpSession session,@RequestParam("name") String product_name){
        int pageCurrent=1;
        Pageable pageable = new PageRequest(0,2,Sort.Direction.DESC,"id");
        String name ="%"+product_name+"%";
        Page<Product> page = productDao.findByNameLike(name,pageable);
        List<Product> productList = page.getContent();
        System.out.println(productList.size());
        session.setAttribute("pd",productList);
        session.setAttribute("totalPage",page.getTotalPages());
        session.setAttribute("currentPage",pageCurrent);
        session.setAttribute("product_name",product_name);
        return "product_list";
    }
    @RequestMapping("pagetitle.do")
    public String Pagetitle(HttpSession session,@RequestParam("category") String category){
        int pageCurrent=1;
        Page<Product> page;
        Pageable pageable = new PageRequest(0,2,Sort.Direction.DESC,"id");
        String name ="%"+category+"%";
        if(!category.equals("all")){
        page = productDao.findByCategoryLike(name,pageable);
        }
        else {
            page = productDao.findAll(pageable);
        }
        List<Product> productList = page.getContent();
        System.out.println(productList.size());
        session.setAttribute("pd",productList);
        session.setAttribute("totalPage",page.getTotalPages());
        session.setAttribute("currentPage",pageCurrent);
        session.setAttribute("category",category);
        return "Pagetitle";
    }
    @RequestMapping("pagetitlelist.do")
    public String Pagetitlelist(@RequestParam("currentPage") String currentPage, HttpSession session){
        if(currentPage!=null) {
            Page<Product> page;
            int pageCurrent = Integer.parseInt(currentPage);
            Pageable pageable = new PageRequest(pageCurrent-1,2,Sort.Direction.DESC,"id");
            String name ="%"+session.getAttribute("category")+"%";
            if(!session.getAttribute("category").equals("all")){
               page  = productDao.findByCategoryLike(name,pageable);
            }
            else {
                page = productDao.findAll(pageable);
            }

            List<Product> productList = page.getContent();
            System.out.println(productList.size());
            session.setAttribute("pd",productList);
            session.setAttribute("totalPage",page.getTotalPages());
            session.setAttribute("currentPage",pageCurrent);
        }
        System.out.println(currentPage+"--------------current pagetitlelist.do");
        return "Pagetitle";
    }
    @RequestMapping("findProductInfo.do")
    public String findProductInfo(@RequestParam("id") String id,HttpSession session){
        Product product = productDao.findOne(Integer.parseInt(id));
        session.setAttribute("productinfo",product);
        return "product_info";
    }
    @RequestMapping("addCart.do")

    public String addCart(@RequestParam("id") String id,HttpSession session){
        Product product = productDao.findOne(Integer.parseInt(id));
        Map<Product,String> cart = (Map<Product,String>)session.getAttribute("cart");
        int num = 1;
        if(cart==null){
            cart =new HashMap<Product,String>();
        }
        if(cart.containsKey(product)){
            num=Integer.parseInt(cart.get(product))+1;
        }
        cart.put(product,num+"");
        session.setAttribute("cart",cart);
        return "index";
    }
    @RequestMapping("Cart")
    public  String Cart(){
        return "cart";
    }
    @RequestMapping("changeNum.do")
    public  String changeNum(@RequestParam("id") Integer id,@RequestParam("num") String num,HttpSession session){
        Product b = new Product();
        b.setId(id);
        Map<Product, String> cart = (Map<Product, String>) session.getAttribute("cart");
        //如果商品数据为0，就删除对象
        if("0".equals(num)){
            cart.remove(b);
        }
        //判断如果找到与id相同的书，
        if(cart.containsKey(b)){
            cart.put(b, num);
        }
        return "cart";
    }
}
