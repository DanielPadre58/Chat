package com.tarnished.chat.repository;

import com.tarnished.chat.domain.chat.Message;
import com.tarnished.chat.dto.message.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m inner join m.chat.messages messages where messages.id = ?1")
    Message findByChat_Messages_Id(String id);

    @Query("select m from Message m where m.id = ?1")
    Optional<Message> findMessageById(String id);
}
