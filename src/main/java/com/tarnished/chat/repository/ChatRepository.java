package com.tarnished.chat.repository;

import com.tarnished.chat.domain.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c LEFT JOIN FETCH c.messages WHERE c.id = :id")
    Optional<Chat> findWithMessages(@Param("id") Long id);

    @Query("SELECT c FROM Chat c LEFT JOIN FETCH c.moderators WHERE c.id = :id")
    Optional<Chat> findWithModerators(@Param("id") Long id);

    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :id")
    List<Chat> findChatByUserId(@Param("id") UUID id);
}
