package me.dio.controller;

import jakarta.validation.Valid;
import me.dio.domain.model.User;
import me.dio.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieve a user by its ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return The ResponseEntity with the user and HTTP status.
     */

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Create a new user.
     *
     * @param userToCreate The user to be created.
     * @return The ResponseEntity with the created user and HTTP status.
     */

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User userToCreate) {
        User userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
}
