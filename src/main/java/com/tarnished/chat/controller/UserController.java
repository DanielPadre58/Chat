package com.tarnished.chat.controller;

import com.tarnished.chat.dto.user.CreateUserDTO;
import com.tarnished.chat.dto.user.UpdateUserDTO;
import com.tarnished.chat.dto.user.UserDTO;
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
    public ResponseEntity<UserDTO> create(@RequestBody CreateUserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserDTO user = userService.findById(UUID.fromString(id));
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        userService.deleteById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id,
                                          @RequestBody UpdateUserDTO userDto) {
        UserDTO updatedUser = userService.update(UUID.fromString(id), userDto);
        if(updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }

        return ResponseEntity.notFound().build();
    }
}
