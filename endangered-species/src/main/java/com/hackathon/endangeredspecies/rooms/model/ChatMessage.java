package com.hackathon.endangeredspecies.rooms.model;

public class ChatMessage {

    public String username;
    public String message;

    public ChatMessage(String username, String message){
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessageText() {
        return message;
    }

}

