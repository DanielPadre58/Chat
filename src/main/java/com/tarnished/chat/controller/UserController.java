package com.tarnished.chat.controller;

import com.tarnished.chat.dto.user.CreateUserDTO;
import com.tarnished.chat.dto.user.UpdateUserDTO;
import com.tarnished.chat.domain.user.User;
import com.tarnished.chat.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody CreateUserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        var user = userService.findById(UUID.fromString(id));
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        userService.deleteById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id,
                                       @RequestBody UpdateUserDTO userDto) {
        var updatedUser = userService.update(UUID.fromString(id), userDto);
        if(updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }

        return ResponseEntity.notFound().build();
    }
}
