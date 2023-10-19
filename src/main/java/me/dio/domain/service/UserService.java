package me.dio.domain.service;

import me.dio.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();
    User findById(UUID id);
    User create(User userToCreate);
    void delete(UUID id);

}
