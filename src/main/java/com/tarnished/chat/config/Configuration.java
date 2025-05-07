package com.tarnished.chat.config;

import com.tarnished.chat.controller.ChatController;
import com.tarnished.chat.controller.MessageController;
import com.tarnished.chat.repository.ChatRepository;
import com.tarnished.chat.repository.UserRepository;
import com.tarnished.chat.service.chat.ChatService;
import com.tarnished.chat.service.chat.MessageService;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ChatController chatController(ChatService chatService) {
        return new ChatController(chatService);
    }

    @Bean
    public MessageController messageController(MessageService messageService, ChatService chatService) {
        return new MessageController(messageService, chatService);
    }

    public ChatService chatService(ChatRepository chatRepository, UserRepository userRepository) {
        return new ChatService(chatRepository, userRepository);
    }
}
