package com.tarnished.chat.dto.chat;

import com.tarnished.chat.domain.chat.Chat;
import com.tarnished.chat.domain.chat.ChatType;
import com.tarnished.chat.domain.chat.Message;
import com.tarnished.chat.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ChatDTO(String name, ChatType type) {
    public ChatDTO(Chat chat){
        this(
                chat.getName(),
                chat.getType()
                );
    }
}
