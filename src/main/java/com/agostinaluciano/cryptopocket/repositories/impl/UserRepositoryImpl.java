package com.agostinaluciano.cryptopocket.repositories.impl;

import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {


    private static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userMapper = (rs, rownum) -> new User(rs.getInt("id"), rs.getString("username"),
            rs.getString("password"), rs.getString("email"));

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> getAll() {
        LOGGER.info("getting all from repository");
        List<User> result = jdbcTemplate.query("SELECT id, username, password, email FROM \"users\"", userMapper);
        return result;
    }

    @Override
    public User getOne(int id) {
        LOGGER.info("getting user with id:{} from repository", id);
        User user = null;
        try {
            user = jdbcTemplate.query("SELECT id, username, password, email FROM \"users\" WHERE id = ?",
                    userMapper, id).get(0);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO \"users\"(username, password, email) VALUES (?, ?, ?)",
                 user.getUsername(), user.getPassword(), user.getEmail());
    }
}
