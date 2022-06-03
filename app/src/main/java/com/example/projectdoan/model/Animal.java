package com.example.projectdoan.model;


import android.graphics.Bitmap;

public class Animal {
    private Bitmap id;
    private String name;

    public Animal(Bitmap id, String name) {
        this.id = id;
        this.name = name;
    }

    public Bitmap getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
