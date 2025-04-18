package com.tarnished.chat.dto.chat;

import com.tarnished.chat.domain.chat.ChatType;

public record CreateChatDTO(String creatorId, String directUserId, String chatName) {
}
