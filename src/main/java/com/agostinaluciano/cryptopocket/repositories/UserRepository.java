package com.agostinaluciano.cryptopocket.repositories;

import com.agostinaluciano.cryptopocket.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User getOne(int id);

    void save(User user);

    void modifyUser(int id, User user);

    void duplicateUser(int id);

    void deleteUser(int id);
}
