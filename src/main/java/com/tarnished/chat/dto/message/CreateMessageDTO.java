package com.tarnished.chat.dto.message;

import com.tarnished.chat.domain.chat.Message;

import java.util.UUID;

public record CreateMessageDTO(Long id, String text, UUID senderId, Long replyingToId) {
    public CreateMessageDTO(Message message){
        this(
                message.getId(),
                message.getText(),
                message.getSender().getId(),
                message.getReplyingTo() == null ? null : message.getReplyingTo().getId()
        );
    }
}
