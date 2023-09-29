package com.example.kafka.controller;

import com.example.kafka.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> publish(@RequestBody MessageRequest messageRequest) {
    this.kafkaTemplate.send("amigoscode", messageRequest.message());
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
