package com.iamdevelop.backend.api;

import com.iamdevelop.backend.business.ChatBusiness;
import com.iamdevelop.backend.model.MChatMessageRequest;
import com.iamdevelop.backend.model.ChatMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatAPI {

    private final ChatBusiness chatBusiness;

    public ChatAPI(ChatBusiness chatBusiness){
        this.chatBusiness = chatBusiness;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> chat(@RequestBody MChatMessageRequest request){
        chatBusiness.getMessage(request);
        return ResponseEntity.ok().build();
    }
}
