package me.dio.domain.service;

import me.dio.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {
    User findById(UUID id);
    User create(User userToCreate);

}
