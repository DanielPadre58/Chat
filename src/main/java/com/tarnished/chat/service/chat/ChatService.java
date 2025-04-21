package com.tarnished.chat.service.chat;

import com.tarnished.chat.domain.chat.Chat;
import com.tarnished.chat.domain.chat.ChatType;
import com.tarnished.chat.domain.user.User;
import com.tarnished.chat.dto.chat.ChatDTO;
import com.tarnished.chat.dto.chat.CreateChatDTO;
import com.tarnished.chat.repository.ChatRepository;
import com.tarnished.chat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public ChatDTO createChat(CreateChatDTO chatDTO, ChatType chatType) {
        Chat chat = new Chat();

        if (chatType == ChatType.Group) {
            User creator = userRepository.findUserById(UUID.fromString(chatDTO.creatorId()));

            chat.setType(chatType);
            chat.setName(chatDTO.chatName());
            chat.addUser(creator);
            chat.addModerator(creator);
            chat.setCreatedAt(LocalDateTime.now());
        }
        else if (chatType == ChatType.Direct){
            User user1 = userRepository.findUserById(UUID.fromString(chatDTO.creatorId()));
            User user2 = userRepository.findUserById(UUID.fromString(chatDTO.directUserId()));

            chat.setType(chatType);
            chat.setName(chatDTO.chatName());
            chat.addUser(user1);
            chat.addUser(user2);
            chat.addModerator(user1);
            chat.addModerator(user2);
            chat.setCreatedAt(LocalDateTime.now());
        }

        return new ChatDTO(chatRepository.save(chat));
    }

    public void deleteChatById(Long id) {
        chatRepository.deleteById(id);
    }

    public Optional<ChatDTO> getChatById(Long id) {
        return chatRepository.findById(id).map(ChatDTO::new);
    }
}
