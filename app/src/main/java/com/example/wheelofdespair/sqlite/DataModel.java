package com.example.wheelofdespair.sqlite;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String[] getInputAsArray() {
        return new String[]{input};
    }

    public static String[] getNamesArray(List<DataModel> dataModels) {
        List<String> namesList = new ArrayList<>();
        for (DataModel dataModel : dataModels) {
            namesList.addAll(Arrays.asList(dataModel.getInputAsArray()));
        }
        return namesList.toArray(new String[0]);
    }
}
