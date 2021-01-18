package com.hackathon.endangeredspecies.rooms.controller;

import java.util.ArrayList;
import java.util.List;

import com.hackathon.endangeredspecies.auth.mapper.UserMapper;
import com.hackathon.endangeredspecies.rooms.mapper.RoomMapper;
import com.hackathon.endangeredspecies.rooms.model.ChatForm;
import com.hackathon.endangeredspecies.rooms.model.ChatMessage;
import com.hackathon.endangeredspecies.rooms.model.Room;
import com.hackathon.endangeredspecies.rooms.service.MessageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;


@Controller
public class ChatController {

    private MessageService messageService;
    private final UserMapper userMapper;
    private RoomMapper roomMapper;

    public ChatController(MessageService messageService, UserMapper userMapper, RoomMapper roomMapper){
        this.userMapper = userMapper;
        this.messageService = messageService;
        this.roomMapper = roomMapper;
    }

    @RequestMapping("/chat")
    public String getChatPage(@RequestParam int roomId, ChatForm chatForm, Model model){
        if(this.roomMapper.selectRoom(roomId) == null){
            System.out.println("Room Could Not Be Found");
            return "home";
        }
        Room room = this.roomMapper.selectRoom(roomId);
        model.addAttribute("roomName", room.getRoomName());
        model.addAttribute("chatMessages", this.messageService.getMessages());
        ArrayList<String> usernames = new ArrayList<String>();
        int maxCount = userMapper.getMaxId();
        for(int i = 2; i<maxCount; i++){
            usernames.add(userMapper.getUsernameById(i));
        }
        model.addAttribute("roomId", roomId);
        model.addAttribute("theUserNames", usernames);
        return "chat";
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public String addMessage(@ModelAttribute("chatForm") ChatForm chatForm, @RequestParam int roomId, Model model){
        System.out.println(chatForm.getMessageText());
        ArrayList<String> usernames = new ArrayList<String>();
        int maxCount = userMapper.getMaxId();
        for(int i = 2; i<maxCount; i++){
            usernames.add(this.userMapper.getUsernameById(i));
        }
        if(this.roomMapper.selectRoom(roomId) == null){
            System.out.println("Room Could Not Be Found");
            return "home";
        }
        Room room = this.roomMapper.selectRoom(roomId);
        model.addAttribute("roomName", room.getRoomName());
        model.addAttribute("theUserNames", usernames);
        model.addAttribute("roomId", roomId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName2 = authentication.getName();
        ChatForm chatFormSent = new ChatForm(currentPrincipalName2, chatForm.getMessageText(), chatForm.getMessageType());
        messageService.addMessage(chatFormSent);
        chatForm.setMessageText("");
        model.addAttribute("chatMessages", this.messageService.getMessages());
        return "chat";
    }

    @GetMapping("/refresh")
    public @ResponseBody List<ChatMessage> refreshMessages(){
        return messageService.getMessages();
    }

}
