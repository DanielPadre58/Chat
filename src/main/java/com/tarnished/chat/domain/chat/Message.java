package com.tarnished.chat.domain.chat;

import com.tarnished.chat.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name = "replying_to")
    private Message replyingTo;

    private boolean edited;
}
