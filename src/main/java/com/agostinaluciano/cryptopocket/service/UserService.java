package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.domain.User;

import java.util.List;

public interface UserService {

  List<User> getAll();

  User getOne(int id);

  void saveUser(User user);

  void modifyUser(int id, User user);

  void duplicateUser(int id);

  void deleteUser(int id);

}
