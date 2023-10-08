package com.example.app.api;

import com.example.app.data.Message;
import com.example.app.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DemoApi {

    private final MessageService messageService;

    public DemoApi(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String index() {
        return "it works";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{identifier}")
    public ResponseEntity<Message> getMessage(@PathVariable UUID identifier) {
        return messageService.getMessage(identifier).map(m -> ResponseEntity.ok(m)).orElse(ResponseEntity.notFound().build());
    }
}
