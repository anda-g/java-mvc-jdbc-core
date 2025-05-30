package model.enities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String uuid;
    private String name;
    private Double price;
    private LocalDate expiryDate;
}
