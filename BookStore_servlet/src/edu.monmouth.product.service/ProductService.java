package edu.monmouth.product.service;

import edu.monmouth.product.dao.ProductDao;
import edu.monmouth.product.domain.PageBean;
import edu.monmouth.product.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    ProductDao pd = new ProductDao();
    public List<Product> findallproducts(){
        try {
            return pd.findAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    //add books
    public void addBook(Product product){
        try {
            pd.addBook(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Product findBookById(String id){
        try {
            return pd.findBookById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
   return null;
    }
    public void deleteBook(String id){
        try {
            pd.delBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleAllBooks(String[] ids){
        try {
            pd.deleAllBooks(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void updateProduct(Product product){
        try {
            pd.updateBook(product);
        } catch (SQLException e) {
            System.out.println(product.getDescription()+"------------chuwentil ----------------");
            e.printStackTrace();
        }
    }

    public List<Product> searchBooks(String id, String category, String name, String minprice, String maxprice) throws SQLException {
       return pd.searchBooks(id,category,name,minprice,maxprice);
    }

    public PageBean findProductsPage(int currentpage, int pagesize,String category) throws SQLException {
        int count = pd.count(category);
        int totalPage = (int) Math.ceil(count*0.1/pagesize);
        List<Product> products = pd.findProducts(currentpage,pagesize,category);
        PageBean pd = new PageBean();
        pd.setCount(count);
        pd.setCurrentPage(currentpage);
        pd.setProducts(products);
        pd.setTotalPage(totalPage);
        pd.setCategory(category);
        return pd;
    }
}
