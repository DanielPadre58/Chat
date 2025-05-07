package com.tarnished.chat.dto.message;

import com.tarnished.chat.domain.chat.Message;

import java.util.UUID;

public record MessageDTO(Long id, String text) {
    public MessageDTO(Message message){
        this(
                message.getId(),
                message.getText()
        );
    }
}
