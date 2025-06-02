package mapper;

import model.dto.ProductCreateDto;
import model.dto.ProductResponseDto;
import model.enities.Product;

import java.util.Random;
import java.util.UUID;

public class ProductMapper {
    public static ProductResponseDto mapFromProductToProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getUuid(),
                product.getName(),
                product.getExpiryDate()
        );
    }

    public static Product mapFromProductCreateDtoToProduct(ProductCreateDto productCreateDto) {
        return new Product(
                new Random().nextInt(999999999),
                UUID.randomUUID().toString(),
                productCreateDto.name(),
                productCreateDto.expiryDate()
        );
    }
}
