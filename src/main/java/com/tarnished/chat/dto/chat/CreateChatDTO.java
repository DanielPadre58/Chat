package com.tarnished.chat.dto.chat;

public record CreateChatDTO(String creatorId, String directUserId, String chatName) {
}
