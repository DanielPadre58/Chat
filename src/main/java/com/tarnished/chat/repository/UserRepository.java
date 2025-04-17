package com.tarnished.chat.repository;

import com.tarnished.chat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID> {
}
