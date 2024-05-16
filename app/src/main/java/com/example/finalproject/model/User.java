package com.example.finalproject.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String password;
    private String username;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
