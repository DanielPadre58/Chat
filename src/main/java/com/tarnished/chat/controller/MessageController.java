package com.tarnished.chat.controller;

import com.tarnished.chat.dto.message.CreateMessageDTO;
import com.tarnished.chat.dto.message.EditMessageDTO;
import com.tarnished.chat.dto.message.MessageDTO;
import com.tarnished.chat.service.chat.ChatService;
import com.tarnished.chat.service.chat.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chats/{chatId}")
public class MessageController {
    private final MessageService messageService;
    private final ChatService chatService;

    public MessageController(MessageService messageService, ChatService chatService) {
        this.messageService = messageService;
        this.chatService = chatService;
    }

    @PostMapping("/post")
    private ResponseEntity<MessageDTO> createMessage(
            @RequestBody CreateMessageDTO createMessageDTO,
            @PathVariable(name = "chatId") Long chatId
    ) {
        if(!chatService.chatExists(chatId)){
            throw new RuntimeException("Chat with id " + chatId + " doesn't exists");
        }

        return ResponseEntity.ok(messageService.createMessage(createMessageDTO, chatId));
    }

    @PatchMapping("/{messageId}/edit")
    private ResponseEntity<MessageDTO> editMessage(
            @PathVariable(name = "messageId") Long messageId,
            @PathVariable(name = "chatId") Long chatId,
            @RequestBody EditMessageDTO editMessageDTO)
            {
        if(!chatService.chatExists(chatId)){
            throw new RuntimeException("Chat with id " + chatId + " doesn't exists");
        }

        return ResponseEntity.ok(messageService.editMessage(messageId, editMessageDTO));
    }

    @DeleteMapping("/{messageId}/delete")
    private ResponseEntity<Void> deleteMessageById(
            @PathVariable(name = "messageId") Long messageId,
            @PathVariable(name = "chatId") Long chatId
    ) {
        messageService.deleteMessageById(messageId);
        return ResponseEntity.noContent().build();
    }
}
