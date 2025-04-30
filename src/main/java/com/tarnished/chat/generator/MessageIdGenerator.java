package com.tarnished.chat.generator;

import com.tarnished.chat.domain.chat.Chat;
import com.tarnished.chat.domain.chat.Message;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MessageIdGenerator implements IdentifierGenerator {
    @Override
    public String generate(SharedSessionContractImplementor session,
                           Object object){
        Message message = (Message) object;
        Chat chat = message.getChat();
        Long chatMessagesCount = chat.getMessageCount();
        return chat.getId() + "-" + (chatMessagesCount + 1);
    }
}
