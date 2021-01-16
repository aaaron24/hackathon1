package com.hackathon.endangeredspecies.rooms.model;

public class Room {

    private int id;
    private String roomName;
    private String roomCreator;

    public Room(){

    }

    public Room(int id, String roomName, String roomCreator){
        this.id = id;
        this.roomName = roomName;
        this.roomCreator = roomCreator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCreator() {
        return roomCreator;
    }

    public void setRoomCreator(String roomCreator) {
        this.roomCreator = roomCreator;
    }
    
}
