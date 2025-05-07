package com.tarnished.chat.dto.user;

import com.tarnished.chat.domain.user.User;
import java.util.UUID;

public record UserDTO(UUID id, String username) {
    public UserDTO(User user) {
        this(
                user.getId(),
                user.getUsername()
        );
    }
}
