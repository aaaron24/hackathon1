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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/createRoom")
    public String getCreateRoom(@ModelAttribute("createdRoom") CreateRoom createRoom, Model model){
        return "home";
    }

    @PostMapping("/createRoom")
    public String createRoom( @RequestParam(value = "roomName", required = false) String roomName,
                              @RequestParam(value = "animal", required = false) String animal,
                              Model model){
        CreateRoom createRoom = new CreateRoom(roomName, animal);
        if(this.roomMapper.selectRoomByName(createRoom.getRoomName())!=null){
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
        room.setRoomName(createRoom.getRoomName());
        room.setAnimal(createRoom.getAnimal());
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
    public String joinRoom(Model model, @RequestParam(value = "roomId", required = false) Integer roomId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = this.userMapper.getUser(authentication.getName()).getUserId();
        int maxId = this.roomUserMapper.getMaxRoomUser();
        RoomUser roomUser = new RoomUser(maxId, userId, roomId);
        return "home";
    }

    @PostMapping("/searchRoom")
    public String searchRoom(Model model, @RequestParam(value = "keyword", required = false) String keyword){
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
