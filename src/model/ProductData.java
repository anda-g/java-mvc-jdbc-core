package model;

import model.enities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductData {
    public static List<Product> products = new ArrayList<>(
            List.of(
                    new Product(
                            1,
                        UUID.randomUUID().toString(),
                        "Coca Cola",
                        LocalDate.of(2026, 12,12)
                    )
            )
    );
}
