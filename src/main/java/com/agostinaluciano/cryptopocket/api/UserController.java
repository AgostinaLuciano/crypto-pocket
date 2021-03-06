package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        LOGGER.info("getting all users");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") int id) {

        LOGGER.info(" getting user with id: {}", id);
        User user = userService.getOne(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            LOGGER.info("user with id: {} not found", id);
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}