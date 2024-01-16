package com.iamdevelop.backend.business;

import com.iamdevelop.backend.model.MChatMessageRequest;
import com.iamdevelop.backend.model.ChatMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatBusiness {
    private final SimpMessagingTemplate template;

    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void getMessage(MChatMessageRequest request){
        String destination = "/topic/chat";
        ChatMessage payload = new ChatMessage();
        payload.setFrom("userTest");
        payload.setMessage(request.getMessage());
        template.convertAndSend(destination,payload);
    }
}
