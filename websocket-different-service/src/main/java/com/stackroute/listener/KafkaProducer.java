package com.stackroute.listener;
import com.stackroute.model.ChatMessage;
import com.stackroute.service.WebSocketService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private WebSocketService webSocketService;

    @Autowired
    public KafkaProducer(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }


    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate2;

    private static final String TOPIC="QueryEngine";

    ChatMessage chatMessage;

    public String postservice(ChatMessage chatMessage)
    {


            kafkaTemplate2.send(TOPIC,chatMessage);

        return "Published successfully";
    }

}