package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.coventers.UserConverter;
import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.dto.UserDTO;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import com.agostinaluciano.cryptopocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        log.info("getting all users");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getOne(@PathVariable("id") int id) {

        log.info(" getting user with id: {}", id);
        return userService.getOne(id)//optional user
                .map(UserConverter::toDto)//Optional de userDTO
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
