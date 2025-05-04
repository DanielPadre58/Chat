package com.tarnished.chat.service.chat;

import com.tarnished.chat.domain.chat.Message;
import com.tarnished.chat.dto.message.CreateMessageDTO;
import com.tarnished.chat.dto.message.EditMessageDTO;
import com.tarnished.chat.dto.message.MessageDTO;
import com.tarnished.chat.repository.ChatRepository;
import com.tarnished.chat.repository.MessageRepository;
import com.tarnished.chat.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    private String fullMessageId(Long chatId, Long chatMessageId){
        return chatId + "-" + chatMessageId;
    }

    @Transactional
    public MessageDTO createMessage(CreateMessageDTO createMessageDTO, Long chatId) {
        Message message = new Message();
        message.setChat(
                chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"))
        );
        message.setReplyingTo(
                createMessageDTO.replyingToId() == null ?
                    null : messageRepository.findMessageById(createMessageDTO.replyingToId())
                           .orElseThrow(() -> new RuntimeException("Message being replied not found"))
        );
        message.setText(createMessageDTO.text());
        message.setSender(
                userRepository.findUserById(createMessageDTO.senderId())
        );
        message.setSentAt(LocalDateTime.now());

        return new MessageDTO(messageRepository.save(message));
    }

    public MessageDTO editMessage(Long messageId, EditMessageDTO editMessageDTO) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.setText(editMessageDTO.text());
        message.setEdited(true);
        return new MessageDTO(messageRepository.save(message));
    }
}
