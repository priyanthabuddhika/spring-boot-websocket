package com.example.springbootwebsocket.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/hello")
    public void greeting(String message, Authentication authentication) throws InterruptedException {
        String username = authentication.getName();

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000); // simulated delay
            this.simpMessagingTemplate.convertAndSendToUser(username,
                    "/queue/greetings", "Hello, " + message);
        }
    }
}
