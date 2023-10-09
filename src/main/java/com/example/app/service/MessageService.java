package com.example.app.service;

import com.example.app.data.Message;
import com.example.app.data.MessageRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final JdbcTemplate jdbcTemplate;

    public MessageService(MessageRepository messageRepository, JdbcTemplate jdbcTemplate) {
        this.messageRepository = messageRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }



    public Optional<Message> getMessage(UUID identifier) {
        return messageRepository.findOneMessageByIdentifier(identifier);
    }

    @Transactional
    public Message createMessage(Message message) {
        if (message.getIdentifier() == null) {
            message.setIdentifier(UUID.randomUUID());
        }
        return messageRepository.save(message);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteMessage(UUID identifier) {
        messageRepository.deleteMessageByIdentifier(identifier);
    }
}
