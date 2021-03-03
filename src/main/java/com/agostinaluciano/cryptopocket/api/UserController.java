package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") int id) {

        User user = userService.getOne(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        else
            return ResponseEntity.notFound().build();
    }
}