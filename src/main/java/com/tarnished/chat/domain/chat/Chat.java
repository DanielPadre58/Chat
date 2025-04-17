package com.tarnished.chat.domain.chat;

import com.tarnished.chat.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @OneToMany
    private List<User> usersId = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @OneToMany
    private List<Message> messages;

    @ManyToMany
    private List<User> moderators;
}
