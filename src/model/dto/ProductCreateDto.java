package model.dto;

import java.time.LocalDate;

public record ProductCreateDto(
        String name,
        LocalDate expiryDate
) {
}
