package com.agostinaluciano.cryptopocket.service.impl;


import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import com.agostinaluciano.cryptopocket.repositories.UserRepository;
import com.agostinaluciano.cryptopocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Optional<User> getOne(int id) {
        return userRepository.getOne(id);
    }

    @Override

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void modifyUser(int id, User user) {
        userRepository.modifyUser(id, user);
    }

    @Override
    public void duplicateUser(int id) {
        userRepository.duplicateUser( id);
    }
    @Override
    public void deleteUser(int id){
        userRepository.deleteUser(id);}

    @Override
    public void validateUser(Integer userId) {
        log.info("User service: Validating user with id: {}", userId);
        getOne(userId).orElseThrow(() -> new UserNotFoundException());
    }


}
