package com.stackroute.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


@Configuration                // to indicate that it is a Spring configuration class
@EnableWebSocketMessageBroker //enables WebSocket message handling, backed by a message broker.

public class WebSocketConfiguration  extends AbstractWebSocketMessageBrokerConfigurer {
    /*Registration of stompEndPoints
     * method registers the "/socket" endpoint, enabling SockJS fallback
     * options so that alternate transports may be used if WebSocket is not
     * available. The SockJS client will attempt to connect to "/socket" and
     * use the best transport available
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();  //library for creating the socket
    }

    /* method implements the default method in WebSocketMessageBrokerConfigurer
     * to configure the message broker
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")      //for project specific
                .enableSimpleBroker("/topic");  //topic for subscription,to enable a simple memory-based message broker to carry the greeting messages back to the client on destinations prefixed with "/chat".
    }
}
