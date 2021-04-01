package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import com.agostinaluciano.cryptopocket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    public User getOne(@PathVariable("id") int id) {

        LOGGER.info(" getting user with id: {}", id);
        return userService.getOne(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void modifyUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.modifyUser(id, user);
    }

    @PutMapping("/dup{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void duplicateUser(@PathVariable("id") int id) {
        userService.duplicateUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

}
