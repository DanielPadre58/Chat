package com.tarnished.chat.domain.chat;

import com.tarnished.chat.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ChatType type;

    @ManyToMany
    @JoinTable(
            name = "chat_users",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "chat_moderators",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "moderator_id")
    )
    private List<User> moderators = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public void addModerator(User moderator) {
        moderators.add(moderator);
    }

    public void removeModerator(User moderator) {
        moderators.remove(moderator);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
