package com.hackathon.endangeredspecies.auth.model;

public class User {

    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;
    private String email;

    public User(Integer userId, String username, String password, String salt, String firstName, String lastName, String email){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salt = salt;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

