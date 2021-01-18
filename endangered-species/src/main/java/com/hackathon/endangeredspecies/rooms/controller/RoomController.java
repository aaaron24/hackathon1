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

    @PostMapping("/createRoom")
    public String createRoom( @RequestParam(value = "roomName", required = false) String roomName,
                              @RequestParam(value = "animal", required = false) String animal,
                              Model model){
        CreateRoom createRoom = new CreateRoom(roomName, animal);
        if(this.roomMapper.selectRoomByName(createRoom.getRoomName())!=null){
            System.out.println("Could not create room");
            return "home";
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
        room.setMoney(0);
        int maxRCId = 0;
        if(roomCreatorMapper.getMaxRoomCreator()!=null){
            maxRCId = roomCreatorMapper.getMaxRoomCreator();
        }
        maxRCId++;
        int userId = this.userMapper.getUser(authentication.getName()).getUserId();
        RoomCreator roomCreator = new RoomCreator(maxRCId, userId, maxId);
        roomCreatorMapper.insertRoomCreator(roomCreator);
        roomMapper.insertRoom(room);
        return "redirect:/your-rooms";
    }
    
    
    

    @PostMapping("/joinRoom")
    public String joinRoom(Model model, @RequestParam(value = "roomId", required = false) Integer roomId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = this.userMapper.getUser(authentication.getName()).getUserId();
        int maxId = this.roomUserMapper.getMaxRoomUser();
        RoomUser roomUser = new RoomUser(maxId, userId, roomId);
        return "home";
    }

    @PostMapping("/search")
    public String searchRoom(Model model, @RequestParam(value = "keyword", required = false) String keyword){
        List<String> roomNames = this.roomMapper.selectRoomNames();
        List<String> animals = this.roomMapper.selectRoomAnimals();
        ArrayList<String> matches = new ArrayList<>();
        ArrayList<String> matchesAnimal = new ArrayList<>();
        String keyword1 = keyword.toLowerCase();
        for(int i = 0; i<roomNames.size(); i++){
            String name = roomNames.get(i).toLowerCase();
            if((name.equals(keyword1) || name.contains(keyword1))){
                matches.add(roomNames.get(i));
            }
        } 
        for(int j = 0; j<animals.size(); j++){
            String name = animals.get(j).toLowerCase();
            if((name.equals(keyword1) || name.contains(keyword1))){
                matchesAnimal.add(animals.get(j));
            }
        }
        ArrayList<Room> rooms = new ArrayList<>();
        for(int i = 0; i<matches.size(); i++){
            Room room = this.roomMapper.selectRoomByName(matches.get(i));
            rooms.add(room);
        }
        for(int j = 0; j<matchesAnimal.size(); j++){
            Room room = this.roomMapper.selectRoomByAnimal(matchesAnimal.get(j));
            rooms.add(room);
        }
        model.addAttribute("searchResults", rooms);
        System.out.println(rooms.get(0));
        return "home";
    }

    @GetMapping("/rooms")
    public String returnRooms(Model model){
        List<Room> list = this.roomMapper.selectRooms();
        return "home";
    }

    @RequestMapping("/room")
    public String returnRoom(@RequestParam int roomId, Model model){
        if(this.roomMapper.selectRoom(roomId) == null){
            System.out.println("Room Could Not Be Found");
            return "home";
        }
        Room room = this.roomMapper.selectRoom(roomId);
        model.addAttribute("chatId", roomId);
        model.addAttribute("roomName", room.getRoomName());
        return "newRoom";
    }

}
