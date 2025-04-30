package com.tarnished.chat.repository;

import com.tarnished.chat.domain.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c LEFT JOIN FETCH c.messages WHERE c.id = :id")
    Optional<Chat> findWithMessages(@Param("id") Long id);

    @Query("SELECT c FROM Chat c LEFT JOIN FETCH c.moderators WHERE c.id = :id")
    Optional<Chat> findWithModerators(@Param("id") Long id);

    @Query("select count(distinct messages) from Chat c inner join c.messages messages where messages.chat.id = ?1")
    long getMessageCount(@NonNull Long id);
}
