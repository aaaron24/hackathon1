package com.hackathon.endangeredspecies.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.hackathon.endangeredspecies.auth.mapper.UserMapper;
import com.hackathon.endangeredspecies.auth.model.User;
import com.hackathon.endangeredspecies.rooms.mapper.RoomCreatorMapper;
import com.hackathon.endangeredspecies.rooms.mapper.RoomMapper;
import com.hackathon.endangeredspecies.rooms.mapper.RoomUserMapper;
import com.hackathon.endangeredspecies.rooms.model.Room;


@Controller
public class YourRoomsController {
	
	
	@Autowired
	  private RoomMapper roomMapper;
	@Autowired
	    private RoomUserMapper roomUserMapper;
	@Autowired
	    private RoomCreatorMapper roomCreatorMapper;
	@Autowired
	    private UserMapper userMapper;
	@RequestMapping(value="/your-rooms", method = RequestMethod.GET)
	public String displayRooms(Model model) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String userName = this.userMapper.getUser(authentication.getName()).getUsername();
		 List<Room> rooms = (List<Room>) roomMapper.roomsCreated(userName);
		model.addAttribute("list", rooms);
		 return "yourRooms";
	} 
	@RequestMapping(value = "/delete-room", method = RequestMethod.GET)
	public String deleteRoom(Model model, @RequestParam String roomName) {
		
		roomMapper.deleteRoom(roomName);
		return "redirect:/your-rooms";
	}
	
	@RequestMapping(value = "/edit-room", method =RequestMethod.GET)
	public String editRoom(Model model, @RequestParam String roomName) {
		Room room = roomMapper.selectRoomByName(roomName);
		model.addAttribute("room", room);
		return "editRoom"; 
	} 
	
	@PostMapping("/editRoomData")
    public String editRoom(Model model, @RequestParam String roomName, @RequestParam String animal, @RequestParam String oldName) {
		System.out.println(oldName);
		Room room  = roomMapper.selectRoomByName(oldName);
//    	Room room  = (Room) model.getAttribute("room");
    	roomMapper.editRoom(room.getId(), roomName, animal);
    	return "redirect:/your-rooms";
    }
	
}
