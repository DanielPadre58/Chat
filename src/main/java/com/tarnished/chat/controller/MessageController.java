package com.tarnished.chat.controller;

import com.tarnished.chat.dto.message.CreateMessageDTO;
import com.tarnished.chat.dto.message.EditMessageDTO;
import com.tarnished.chat.dto.message.MessageDTO;
import com.tarnished.chat.service.chat.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chats/{chatId}")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/post")
    private ResponseEntity<MessageDTO> createMessage(
            @RequestBody CreateMessageDTO createMessageDTO,
            @PathVariable(name = "chatId") Long chatId
    ) {
        return ResponseEntity.ok(messageService.createMessage(createMessageDTO, chatId));
    }

    @PatchMapping("/{messageId}/edit")
    private ResponseEntity<MessageDTO> editMessage(
            @PathVariable(name = "messageId") Long messageId,
            @PathVariable(name = "chatId") Long chatId,
            @RequestBody EditMessageDTO editMessageDTO)
            {
        return ResponseEntity.ok(messageService.editMessage(messageId, editMessageDTO));
    }
}
