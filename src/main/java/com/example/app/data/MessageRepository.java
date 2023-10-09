package com.example.app.data;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends ListCrudRepository<Message, Long> {

    Optional<Message> findOneMessageByIdentifier(UUID identifier);
    int deleteMessageByIdentifier(UUID identifier);
}
