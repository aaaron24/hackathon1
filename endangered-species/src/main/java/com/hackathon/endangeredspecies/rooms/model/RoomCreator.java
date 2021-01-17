package com.hackathon.endangeredspecies.rooms.model;

public class RoomCreator {

    private int id;
    private int user_id;
    private int room_id;

    public RoomCreator(){

    }

    public RoomCreator(int id, int user_id, int room_id){
        this.id = id;
        this.user_id = user_id;
        this.room_id = room_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
}
