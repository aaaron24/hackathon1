package com.hackathon.endangeredspecies.auth.service;

import com.hackathon.endangeredspecies.auth.mapper.UserMapper;
import com.hackathon.endangeredspecies.auth.model.User;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService){
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        if(userMapper.getUser(username) == null){
            return true;
        }
        else{
            return false;
        }
    }

    public int createUser(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        int maxId = 0;
        if(userMapper.getMaxId() != null){
            maxId = userMapper.getMaxId();
        }
        maxId++;
        User newUser = new User(maxId, user.getUsername(), hashedPassword, encodedSalt, user.getFirstName(), user.getLastName(),user.getEmail());
        int index = userMapper.insertUser(newUser);
        return index;
    }
}

