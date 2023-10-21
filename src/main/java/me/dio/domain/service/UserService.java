package me.dio.domain.service;

import me.dio.domain.dto.UserDto;
import me.dio.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();
    User findById(UUID id);
    User create(User userToCreate);
    User updateUser(UUID id, UserDto userToUpdate);
    void delete(UUID id);

}
