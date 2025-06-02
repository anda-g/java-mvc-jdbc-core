package model.service;

import mapper.ProductMapper;
import model.ProductData;
import model.dao.ProductRepository;
import model.dto.ProductCreateDto;
import model.dto.ProductResponseDto;
import model.dto.UpdateProductDto;
import model.enities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository = new ProductRepository();
    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        productRepository.findAll()
                .forEach(
                        product -> productResponseDtoList.add(new ProductResponseDto(
                                product.getUuid(),
                                product.getName(),
                                product.getExpiryDate()
                        ))
                );
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto insertNewProduct(ProductCreateDto productCreateDto) {
        Product product = ProductMapper.mapFromProductCreateDtoToProduct(productCreateDto);
        return ProductMapper.mapFromProductToProductResponseDto(
                productRepository.save(product)
        );
    }

    @Override
    public Integer deleteProductByUuid(String uuid) {
        Product product = ProductData.products.stream()
                .filter(p->p.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
        if(product != null) {
            return productRepository.delete(product.getId());
        }
        System.out.println("No such product with uuid: " + uuid);
        return 0;
    }

    @Override
    public ProductResponseDto getProductByUuid(String uuid) {
        return ProductMapper.mapFromProductToProductResponseDto(
                productRepository.findProductByUuid(uuid)
        );
    }

    @Override
    public ProductResponseDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto) {
        try{
            return ProductMapper.mapFromProductToProductResponseDto(
                    productRepository.updateProductByUuid(uuid, updateProductDto)
            );
        }catch (NoSuchElementException e){
            System.out.println("An error occur while updating product: " + e.getMessage());
        }
        return null;
    }
}
