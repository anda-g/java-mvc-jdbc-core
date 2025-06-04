package model.service;

import mapper.UserMapper;
import model.dao.UserRepository;
import model.dto.UpdateUserDto;
import model.dto.UserCreateDto;
import model.dto.UserResponseDto;
import model.enities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository = new UserRepository();
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> userResponseDto = new ArrayList<>();
        userRepository.findAll().forEach((user) -> {
            userResponseDto.add(new UserResponseDto(
                    user.getUuid(),
                    user.getEmail(),
                    user.getUserName(),
                    user.getCreatedDate()
            ));
        });
        return userResponseDto;
    }

    @Override
    public UserResponseDto insertNewUser(UserCreateDto userCreateDto) {
        User user = UserMapper.mapFromUserCreateDtoToUser(userCreateDto);
        return UserMapper.mapFromUserToUserResponseDto(userRepository.save(user));
    }

    @Override
    public Integer deleteUserByUuid(String uuid) {
        User user = userRepository.findUserByUuid(uuid);
        if(user != null) {
            return userRepository.delete(user.getId());
        }
        System.err.println("[!] No such user with uuid: " + uuid);
        return 0;
    }

    @Override
    public UserResponseDto getUserByUuid(String uuid) {
        return UserMapper.mapFromUserToUserResponseDto(
                userRepository.findUserByUuid(uuid)
        );
    }

    @Override
    public UserResponseDto updateUserByUuid(String uuid, UpdateUserDto updateUserDto) {
        try{
            return UserMapper.mapFromUserToUserResponseDto(
                    userRepository.updateUserByUuid(uuid, updateUserDto)
            );
        }catch (NoSuchElementException e){
            System.err.println("[!] No such user with uuid: " + uuid);
        }
        return null;
    }
}
