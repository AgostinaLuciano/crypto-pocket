package com.agostinaluciano.cryptopocket.repositories.impl;

import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {


    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> getAll() {
        List<User> result = jdbcTemplate.query("SELECT id, username, password, email FROM \"users\"",
                (rs,rownum) -> new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password"), rs.getString("email") ));
        return result;
    }

    @Override
    public User getOne(int id) {
        User user = null;
       try{ user = jdbcTemplate.query("SELECT id, username, password, email FROM \"users\" WHERE id = ?",
               (rse, rownum) ->  new User(rse.getInt("id"), rse.getString("username"),
                    rse.getString("password"), rse.getString("email")), id).get(0);
    }
       catch (IndexOutOfBoundsException e) {
           e.printStackTrace();
       }
        return user;


    }}
