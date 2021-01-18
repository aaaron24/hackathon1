package com.hackathon.endangeredspecies.rooms.service;


import com.hackathon.endangeredspecies.rooms.model.ChatForm;
import com.hackathon.endangeredspecies.rooms.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<ChatMessage> chatMessages;

    public List<ChatMessage> getMessages(){
        return chatMessages;
    }

    public void addMessage(ChatForm chatForm){
        String message = "";
        if(chatForm.getMessageType()==null){
            message = " No message";
        }
        else if(chatForm.getMessageType().equals("Say")){
            message = " " + chatForm.getMessageText();
        }
        else if(chatForm.getMessageType().equals("Shout")){
            message = " " + chatForm.getMessageText().toUpperCase();
        }
        else{
            message = " " + chatForm.getMessageText().toLowerCase();
        }
        ChatMessage chatMessage = new ChatMessage(chatForm.getUsername() + ": ", message);
        chatMessages.add(chatMessage);
    }

    @PostConstruct
    public void postConstruct(){
        this.chatMessages = new ArrayList<>();
    }
}
