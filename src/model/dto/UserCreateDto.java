package model.dto;

public record UserCreateDto(
        String userName,
        String email,
        String password
) {
}
