package me.dio.domain.service.Impl;

import me.dio.domain.dto.FeatureDto;
import me.dio.domain.dto.NewsDto;
import me.dio.domain.dto.UserDto;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account Number already exists.");
        }
        if(userRepository.existsByCardNumber(userToCreate.getCard().getNumber())){
            throw new IllegalArgumentException("This Card Number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public User updateUser(UUID id, UserDto userToUpdate) {
        var user = this.findById(id);
        if(!user.getId().equals(userToUpdate.id())){
            throw new IllegalArgumentException("Update IDs must to be the same");
        }
        if(userToUpdate.name() != null && !userToUpdate.name().isEmpty()){
            user.setName(userToUpdate.name());
        }
        if(userToUpdate.account() != null){
            user.setAccount(userToUpdate.account().toAccountModel());
        }
        if(userToUpdate.card() != null){
            user.setCard(userToUpdate.card().toCardModel());
        }
        if(userToUpdate.features() != null){
            user.setFeatures(userToUpdate.features().stream().map(FeatureDto::toFeatureModel).toList());
        }
        if(userToUpdate.news() != null){
            user.setNews(userToUpdate.news().stream().map(NewsDto::toNewsModel).toList());
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        if(!userRepository.existsById(id)){
            throw new NoSuchElementException();
        }
        userRepository.deleteById(id);
    }
}
