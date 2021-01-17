package com.hackathon.endangeredspecies.rooms.controller;

import com.hackathon.endangeredspecies.rooms.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoomController {

    @PostMapping("/createRoom")
    public String createRoom(Model model, @ModelAttribute Room room) {

        return "home";
    }

    @GetMapping("/getRooms")
    public String returnRooms() {

        return "home";
    }

}