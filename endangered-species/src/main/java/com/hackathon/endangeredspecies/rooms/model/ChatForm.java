package com.hackathon.endangeredspecies.rooms.model;

public class ChatForm {
    private String username;
    private String messageText;
    private String messageType;

    public ChatForm(String username, String messageText, String messageType){
        this.username = username;
        this.messageText = messageText;
        this.messageType = messageType;
    }

    public String getUsername() {
        return username;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

