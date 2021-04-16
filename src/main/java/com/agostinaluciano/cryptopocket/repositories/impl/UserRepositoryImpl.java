package com.agostinaluciano.cryptopocket.repositories.impl;

import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> getOne(int id) {
        LOGGER.info("getting user with id:{} from repository", id);
        return jdbcTemplate.query("SELECT id, username, password, email FROM \"users\" WHERE id = ?",
                userMapper, id).stream().findFirst();
    }


    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO \"users\"(username, password, email) VALUES (?, ?, ?)",
                user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public void modifyUser(int id, User user) {
        jdbcTemplate.update("UPDATE \"users\" SET username = ?, password = ?, email = ? WHERE id = ?", user.getUsername(),
                user.getPassword(), user.getEmail(), id);
    }

    public void duplicateUser(int id) {
        jdbcTemplate.update("INSERT INTO \"users\" (username, password, email) " +
                "SELECT  username, password, email FROM \"users\" WHERE id = ?", id);
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM \"users\" WHERE id=?", id);

    }
}
