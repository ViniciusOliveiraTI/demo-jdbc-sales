package application;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

import java.util.List;

public class ProgramProduct {

    public static void main(String[] args) {

        ProductDao productDao = DaoFactory.createProductDao();
//
//        System.out.println("==== TEST 1 product insert ====");
//        Product product = new Product(null, "Notebook", 4550.0, 2);
//        productDao.insert(product);
//        System.out.println("Product inserted successfuly");
//
//        System.out.println("==== TEST 2 product update ====");
//        product.setStock(5);
//        productDao.update(product);
//        System.out.println("Product updated successfuly");

        System.out.println("==== TEST 3 product findById ====");
        System.out.println(productDao.findById(1));

        System.out.println("==== TEST 4 product findAll ====");
        List<Product> products = productDao.findAll();
        products.forEach(System.out::println);
//
//        System.out.println("==== TEST 5 product deleteById ====");
//        productDao.deleteById(4);
//        System.out.println("Product deleted!");

    }

}
