package com.agostinaluciano.cryptopocket.repositories;

import com.agostinaluciano.cryptopocket.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();

    Optional<User> getOne(int id);

    void save(User user);
  
    void modifyUser(int id, User user);

    void duplicateUser(int id);

    void deleteUser(int id);

}
