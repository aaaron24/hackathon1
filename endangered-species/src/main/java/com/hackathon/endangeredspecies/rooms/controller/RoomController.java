package com.hackathon.endangeredspecies.rooms.controller;

import com.hackathon.endangeredspecies.auth.mapper.UserMapper;
import com.hackathon.endangeredspecies.auth.model.User;
import com.hackathon.endangeredspecies.rooms.mapper.RoomCreatorMapper;
import com.hackathon.endangeredspecies.rooms.mapper.RoomMapper;
import com.hackathon.endangeredspecies.rooms.mapper.RoomUserMapper;
import com.hackathon.endangeredspecies.rooms.model.Room;
import com.hackathon.endangeredspecies.rooms.model.RoomCreator;
import com.hackathon.endangeredspecies.rooms.model.RoomUser;
import com.hackathon.endangeredspecies.rooms.request.CreateRoom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    private RoomMapper roomMapper;
    private RoomUserMapper roomUserMapper;
    private RoomCreatorMapper roomCreatorMapper;
    private UserMapper userMapper;


    public RoomController(UserMapper userMapper, RoomMapper roomMapper, RoomUserMapper roomUserMapper, RoomCreatorMapper roomCreatorMapper){
        this.roomMapper = roomMapper;
        this.roomCreatorMapper = roomCreatorMapper;
        this.roomUserMapper = roomUserMapper;
        this.userMapper = userMapper;
    }

    @PostMapping("/createRoom")
    public String createRoom(Model model, @ModelAttribute CreateRoom createdRoom){
        if(this.roomMapper.selectRoomByName(createdRoom.getRoomName())==null){
            System.out.println("Could not create room");
            return "chat";
        }
        Room room = new Room();
        int maxId = 0;
        if(roomMapper.getMaxRoom()!=null){
            maxId = roomMapper.getMaxRoom();
        }
        maxId++;
        room.setId(maxId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        room.setRoomCreator(authentication.getName());
        room.setRoomName(createdRoom.getRoomName());
        room.setAnimal(createdRoom.getAnimal());
        int maxRCId = 0;
        if(roomCreatorMapper.getMaxRoomCreator()!=null){
            maxRCId = roomCreatorMapper.getMaxRoomCreator();
        }
        maxRCId++;
        int userId = this.userMapper.getUser(authentication.getName()).getUserId();
        RoomCreator roomCreator = new RoomCreator(maxRCId, userId, maxId);
        roomCreatorMapper.insertRoomCreator(roomCreator);
        roomMapper.insertRoom(room);
        return "home";
    }

    @PostMapping("/joinRoom")
    public String joinRoom(Model model, Integer roomId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = this.userMapper.getUser(authentication.getName()).getUserId();
        int maxId = this.roomUserMapper.getMaxRoomUser();
        RoomUser roomUser = new RoomUser(maxId, userId, roomId);
        return "home";
    }

    @PostMapping("/searchRoom")
    public String searchRoom(Model model, String keyword){
        List<String> roomNames = this.roomMapper.selectRoomNames();
        List<String> animals = this.roomMapper.selectRoomAnimals();
        ArrayList<String> matches = new ArrayList<>();
        for(int i = 0; i<roomNames.size(); i++){
            if((roomNames.get(i).equals(keyword) || roomNames.get(i).contains(keyword))){
                matches.add(roomNames.get(i));
            }
        }
        for(int j = 0; j<animals.size(); j++){
            if((animals.get(j).equals(keyword) || animals.get(j).contains(keyword))){
                matches.add(animals.get(j));
            }
        }
        return "home";
    }

    @GetMapping("/rooms")
    public String returnRooms(Model model){
        List<Room> list = this.roomMapper.selectRooms();
        return "home";
    }

    @GetMapping("/room/{roomId}")
    public String returnRoom(@PathVariable int roomId, Model model){
        if(this.roomMapper.selectRoom(roomId) == null){
            System.out.println("Room Could Not Be Found");
            return "home";
        }
        Room room = this.roomMapper.selectRoom(roomId);
        return "home";
    }
    
    

}
