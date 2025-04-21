package com.tarnished.chat.repository;

import com.tarnished.chat.domain.chat.Chat;
import com.tarnished.chat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findChatById(Long id);
}
