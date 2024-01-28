package com.example.wheelofdespair.sqlite;

import androidx.annotation.NonNull;

public class DataModel {

    private int id;
    private String input;

    public DataModel(int id, String input) {
        this.id = id;
        this.input = input;
    }

    public DataModel() {
    }

    public int getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id +
                ",  Name: " + input;
    }
}
