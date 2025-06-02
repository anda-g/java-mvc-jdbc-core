package model.dao;

import model.ProductData;
import model.dto.UpdateProductDto;
import model.enities.Product;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ProductRepository implements Repository<Product, Integer> {

    @Override
    public Product save(Product product) {
        ProductData.products.add(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return ProductData.products;
    }

    @Override
    public Integer delete(Integer id) {
        return Integer.valueOf(
                String.valueOf(
                        ProductData.products.
                                removeIf(e-> Objects.equals(e.getId(), id))
                )
        );
    }

    public Product findProductByUuid(String uuid){
        return ProductData.products
                .stream()
                .filter(p -> p.getUuid().equals(uuid))
                .findFirst().
                orElse(null);
    }

    public Product updateProductByUuid(String uuid, UpdateProductDto updateProductDto){
        Product product = findProductByUuid(uuid);
        if(product!=null){
            ProductData.products.remove(product);
            product.setName(updateProductDto.name());
            ProductData.products.add(product);
            Collections.reverse(ProductData.products);
            return product;
        }
        throw new NoSuchElementException("Product not found");
    }
}
