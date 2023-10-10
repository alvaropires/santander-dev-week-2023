package me.dio.domain.service;

import me.dio.domain.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User findById(Long id);
    User create(User userToCreate);

}
