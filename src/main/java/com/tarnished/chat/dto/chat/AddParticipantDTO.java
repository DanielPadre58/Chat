package com.tarnished.chat.dto.chat;

import java.util.List;
import java.util.UUID;

public record AddParticipantDTO(List<UUID> newUsersId) {
}
