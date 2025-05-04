package com.tarnished.chat.domain.chat;

import com.tarnished.chat.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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

    private String text;

    @ManyToOne
    @JoinColumn(name = "replying_to")
    private Message replyingTo;

    private boolean edited;
}
