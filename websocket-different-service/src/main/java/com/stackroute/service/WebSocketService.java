package com.stackroute.service;

import com.stackroute.model.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {


    public ChatMessage sendMessageService(ChatMessage message){
        return message;
    }
}
