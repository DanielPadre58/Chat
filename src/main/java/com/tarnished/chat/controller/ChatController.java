package com.tarnished.chat.controller;

import com.tarnished.chat.domain.chat.Chat;
import com.tarnished.chat.domain.chat.ChatType;
import com.tarnished.chat.dto.chat.CreateChatDTO;
import com.tarnished.chat.service.chat.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create/direct")
    private ResponseEntity<Chat> createChat(@RequestBody CreateChatDTO createChatDTO) {
        return ResponseEntity.ok(chatService.createChat(createChatDTO, ChatType.Direct));
    }
}
