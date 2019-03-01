package com.stackroute.listener;

import com.stackroute.model.ChatMessage;
import com.stackroute.service.WebSocketService;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private KafkaProducer kafkaProducer;

    private WebSocketService webSocketService;



    @Autowired
    public KafkaConsumer(WebSocketService webSocketService,
                         KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        this.webSocketService = webSocketService;
    }

    @KafkaListener(topics = "QueryEngine", groupId = "group_id")
    public ChatMessage consume(String message) {

        JSONObject object = (JSONObject) JSONValue.parse(message);
        ChatMessage chatMessage=new ChatMessage(object.get("content").toString()
                ,object.get("sender").toString());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(chatMessage.getSender());
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbb");
        System.out.println(chatMessage.getContent());

        return webSocketService.sendMessageService(chatMessage);

//        kafkaProducer.postservice(chatMessage);
    }
}
