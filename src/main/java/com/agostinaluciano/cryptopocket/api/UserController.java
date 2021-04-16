package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.api.exception.ApiErrorMessage;
import com.agostinaluciano.cryptopocket.coventers.UserConverter;
import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.dto.UserDTO;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import com.agostinaluciano.cryptopocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAll() {

        log.info("getting all users");
        List<UserDTO> userDTOList = userService.getAll()
                .stream()
                .map(user -> (UserConverter.toDto(user)))
                .collect(Collectors.toList());
        return userDTOList;
    }

    @GetMapping("/{id}")
    public UserDTO getOne(@PathVariable("id") int id) {

        log.info(" getting user with id: {}", id);
        return userService.getOne(id)//optional user
                .map(UserConverter::toDto)//Optional de userDTO
                .orElseThrow(() -> new UserNotFoundException());

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
