package com.agostinaluciano.cryptopocket.service.impl;


import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.repositories.UserRepository;
import com.agostinaluciano.cryptopocket.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
       return userRepository.getAll();
    }
}
