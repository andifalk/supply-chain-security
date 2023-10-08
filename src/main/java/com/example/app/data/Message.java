package com.example.app.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Message {

    @Id
    private Long id;
    private UUID identifier;
    private String message;
    private LocalDateTime created;

    @PersistenceCreator
    public Message(Long id, UUID identifier, String message, LocalDateTime created) {
        this.id = id;
        this.identifier = identifier;
        this.message = message;
        this.created = created;
    }

    public Message() {}

    public Long getId() {
        return id;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", identifier=" + identifier +
                ", message='" + message + '\'' +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(id, message1.id) && Objects.equals(identifier, message1.identifier) && Objects.equals(message, message1.message) && Objects.equals(created, message1.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier, message, created);
    }
}
