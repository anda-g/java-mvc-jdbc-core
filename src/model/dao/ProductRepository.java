package model.dao;

import model.enities.Product;

import java.util.List;

public class ProductRepository implements Repository<Product, Integer> {
    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Product product) {

    }
}
