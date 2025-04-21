package com.tarnished.chat.controller;

import com.tarnished.chat.domain.chat.Chat;
import com.tarnished.chat.domain.chat.ChatType;
import com.tarnished.chat.dto.chat.ChatDTO;
import com.tarnished.chat.dto.chat.CreateChatDTO;
import com.tarnished.chat.service.chat.ChatService;
import com.tarnished.chat.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create/direct")
    private ResponseEntity<ChatDTO> createDirectChat(@RequestBody CreateChatDTO createChatDTO) {
        return ResponseEntity.ok(chatService.createChat(createChatDTO, ChatType.Direct));
    }

    @PostMapping("/create/group")
    private ResponseEntity<ChatDTO> createGroupChat(@RequestBody CreateChatDTO createChatDTO) {
        return ResponseEntity.ok(chatService.createChat(createChatDTO, ChatType.Group));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteChatById(@PathVariable Long id) {
        chatService.deleteChatById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<ChatDTO> getChatById(@PathVariable Long id) {
        Optional<ChatDTO> chat = chatService.getChatById(id);
        if (chat.isPresent()) {
            return ResponseEntity.ok(chat.get());
        }

        return ResponseEntity.notFound().build();
    }
}
