package model.dao;

import model.entities.Product;

import java.util.List;

public interface ProductDao {
    void insert(Product product);
    void update(Product product);
    void deleteById(Integer id);
    Product findById(Integer id);
    List<Product> findAll();
}
