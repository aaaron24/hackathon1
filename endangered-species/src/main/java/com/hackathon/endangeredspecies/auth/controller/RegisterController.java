package com.hackathon.endangeredspecies.auth.controller;

import com.hackathon.endangeredspecies.auth.model.User;
import com.hackathon.endangeredspecies.auth.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    private String returnRegisterPage(){
        return "register";
    }

    @PostMapping()
    public String registerUser(@ModelAttribute User user, Model model){
        String registerError = null;
        if(userService.isUsernameAvailable(user.getUsername())==false){
            registerError = "Username already taken";
        }
        if(registerError == null){
            int rowsAdded = userService.createUser(user);
            if(rowsAdded<0){
                return "error";
            }
        }
        if(registerError == null) {
            model.addAttribute("registerSuccess", true);
        }
        else {
            model.addAttribute("registerError", registerError);
        }
        return "register";
    }

}

