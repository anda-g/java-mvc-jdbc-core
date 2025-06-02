package model.dto;

import java.time.LocalDate;

public record ProductResponseDto(
        String uuid,
        String name,
        LocalDate expiryDate
) {
}
