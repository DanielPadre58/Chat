package com.tarnished.chat.service.user;

import com.tarnished.chat.dto.user.UpdateUserDTO;
import com.tarnished.chat.domain.user.User;
import com.tarnished.chat.dto.user.UserDTO;
import com.tarnished.chat.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.tarnished.chat.dto.user.CreateUserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(CreateUserDTO userDto){
        User newUser = new User();
        newUser.setUsername(userDto.username());
        newUser.setEmail(userDto.email());
        newUser.setPassword(userDto.password());
        newUser.setCreatedAt(LocalDateTime.now());

        return new UserDTO(userRepository.save(newUser));
    }

    public UserDTO findById(UUID id) {
        return userRepository.findById(id)
                .map(UserDTO::new)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public UserDTO update(UUID id, UpdateUserDTO userDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));;

        User updatedUser = updateUserFields(user, userDto);
        return new UserDTO(userRepository.save(user));
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
