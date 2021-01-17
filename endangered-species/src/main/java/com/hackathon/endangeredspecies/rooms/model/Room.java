package com.hackathon.endangeredspecies.rooms.model;

public class Room {

    private int id;
    private String roomName;
    private String roomCreator;
    private String animal;
    private int money;

    public Room(){

    }

    public Room(int id, String roomName, String roomCreator, String animal, int money){
        this.id = id;
        this.roomName = roomName;
        this.roomCreator = roomCreator;
        this.animal = animal;
        this.money = money;
    }

    public int getId() {
        return id;
    }
    public int getMoney(){
        return this.money;
    }
    public void setMoney(int money){
        this.money = money;
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

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
