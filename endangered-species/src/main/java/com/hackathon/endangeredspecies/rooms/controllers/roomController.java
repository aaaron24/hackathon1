package com.hackathon.endangeredspecies.rooms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class roomController {
    public roomController(){

    }
    @RequestMapping(value = "/newRoom", method= RequestMethod.GET)
    public String newRoom(){
        return "newRoom";
    }


}
