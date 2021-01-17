package com.hackathon.endangeredspecies.rooms.request;

public class CreateRoom {

    public String roomName;
    public String animal;

    public CreateRoom(String roomName, String animal){
        this.roomName = roomName;
        this.animal = animal;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
