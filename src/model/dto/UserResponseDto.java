package model.dto;

import java.time.LocalDate;

public record UserResponseDto(
        String uuid,
        String email,
        String userName,
        LocalDate createdDate
) {
}
