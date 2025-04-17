package com.tarnished.chat.service;

import com.tarnished.chat.dto.UpdateUserDTO;
import com.tarnished.chat.entity.User;
import com.tarnished.chat.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.tarnished.chat.dto.CreateUserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDTO userDto){
        var newUser = new User();
        newUser.setUsername(userDto.username());
        newUser.setEmail(userDto.email());
        newUser.setPassword(userDto.password());
        newUser.setCreatedAt(LocalDateTime.now());

        return userRepository.save(newUser);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public User update(UUID id, UpdateUserDTO userDto){
        var user = userRepository.findById(id);
        return user.map(value -> updateUserFields(value, userDto)).orElse(null);
    }

    private User updateUserFields(User user, UpdateUserDTO userDto) {
        if(userDto.username() != null){
            user.setUsername(userDto.username());
        }

        if(userDto.password() != null){
            user.setPassword(userDto.password());
        }

        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }
}
