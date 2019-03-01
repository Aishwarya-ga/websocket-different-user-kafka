package com.stackroute.controller;

import com.stackroute.listener.KafkaProducer;
import com.stackroute.model.ChatMessage;
import com.stackroute.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class WebSocketController {
    private final SimpMessagingTemplate template;

    private WebSocketService webSocketService;
    private KafkaProducer kafkaProducer;

    @Autowired
    public WebSocketController(WebSocketService webSocketService,SimpMessagingTemplate template,KafkaProducer kafkaProducer ) {
        this.webSocketService = webSocketService;
        this.kafkaProducer=kafkaProducer;
        this.template = template;
    }


    /*same as @RequestMappingThe @MessageMapping annotation ensures
     *that if a message is sent to destination "/send/message"
     */
//    @MessageMapping("/chat.send/{sessionId}")
    @SendTo("/topic/public/{sessionId}")
    public ChatMessage sendMessage(@Payload ChatMessage message){
        return webSocketService.sendMessageService(message);
    }


    @PostMapping("/addchat")
    public String addKnowledge(@RequestBody ChatMessage chatMessage) {

        return kafkaProducer.postservice(chatMessage);


    }
}
