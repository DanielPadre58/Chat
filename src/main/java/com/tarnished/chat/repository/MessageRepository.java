package com.tarnished.chat.repository;

import com.tarnished.chat.domain.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.id = ?1")
    Optional<Message> findMessageById(String id);
}
