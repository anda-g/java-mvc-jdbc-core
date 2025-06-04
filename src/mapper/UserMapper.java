package mapper;

import model.dto.UserCreateDto;
import model.dto.UserResponseDto;
import model.enities.User;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class UserMapper {
    public static UserResponseDto mapFromUserToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getUuid(),
                user.getEmail(),
                user.getUserName(),
                user.getCreatedDate()
        );
    }
    public static User mapFromUserCreateDtoToUser(UserCreateDto userCreateDto) {
        return new User(
                new Random().nextInt(999999999),
                userCreateDto.userName(),
                userCreateDto.email(),
                UUID.randomUUID().toString(),
                LocalDate.now(),
                userCreateDto.password()
        );
    }
}
