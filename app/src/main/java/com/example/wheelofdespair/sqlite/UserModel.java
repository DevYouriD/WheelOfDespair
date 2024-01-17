package com.example.wheelofdespair.sqlite;

import androidx.annotation.NonNull;

public class UserModel {

    private int id;
    private String username;
    private String password;

    public UserModel(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public String getPassword() { return password; }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id +
                ",  Name: " + username +
                ",  Password: " + password;
    }
}
