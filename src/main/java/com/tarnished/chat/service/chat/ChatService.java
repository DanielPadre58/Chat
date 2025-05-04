package com.tarnished.chat.service.chat;

import com.tarnished.chat.domain.chat.Chat;
import com.tarnished.chat.domain.chat.ChatType;
import com.tarnished.chat.domain.user.User;
import com.tarnished.chat.dto.chat.AddParticipantDTO;
import com.tarnished.chat.dto.chat.ChatDTO;
import com.tarnished.chat.dto.chat.CreateChatDTO;
import com.tarnished.chat.dto.chat.RemoveParticipantDTO;
import com.tarnished.chat.repository.ChatRepository;
import com.tarnished.chat.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

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

    @Transactional
    public ChatDTO getChatById(Long id) {
        Chat chat = chatRepository.findWithMessages(id)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        chatRepository.findWithModerators(id); // segunda query carrega os moderadores

        return new ChatDTO(chat);
    }

    @Transactional
    public void addParticipantToChat(Long chatId, AddParticipantDTO addParticipantDTO) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        if(chat.getType() == ChatType.Direct){
            throw new RuntimeException("Cannot add participant to a direct chat");
        }

        for (UUID id : addParticipantDTO.newUsersId()) {
            User newParticipant = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User with id, " + id.toString() + " not found"));

            if(chat.getUsers().contains(newParticipant)){
                throw new RuntimeException("User with id, " + id.toString() + " is already in chat");
            }

            chat.addUser(newParticipant);
        }
    }

    public void removeParticipantFromChat(Long chatId, RemoveParticipantDTO removeParticipantDTO){
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        if(chat.getType() == ChatType.Direct){
            deleteChatById(chatId);
            return;
        }

        for (UUID id : removeParticipantDTO.usersId()) {
            chat.removeUser(userRepository.findUserById(id));
        }
    }

    @Transactional
    public void updateChatsId(){
        List<Chat> chats = chatRepository.findAll();
        for(int i = 0; i < chats.size(); i++){
            if(chats.get(i).getId() != i + 1){
                chats.get(i).setId((long) i + 1);
            }
        }
    }
}
