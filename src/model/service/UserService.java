package model.service;


import model.dto.UpdateUserDto;
import model.dto.UserCreateDto;
import model.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto insertNewUser(UserCreateDto userCreateDto);
    Integer deleteUserByUuid(String uuid);
    UserResponseDto getUserByUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid, UpdateUserDto updateUserDto);
}
