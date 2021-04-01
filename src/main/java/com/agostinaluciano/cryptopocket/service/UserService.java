package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  List<User> getAll();

  Optional<User> getOne(int id);

  void saveUser(User user);


  void modifyUser(int id, User user);

  void duplicateUser(int id);

  void deleteUser(int id);
}
