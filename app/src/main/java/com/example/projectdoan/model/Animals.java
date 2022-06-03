package com.example.projectdoan.model;


import android.graphics.Bitmap;

public class Animals {
    private Bitmap id;
    private String txtname;

    public Animals(Bitmap id, String txtname) {
        this.id = id;
        this.txtname = txtname;
    }

    public Bitmap getId() {return id;}

    public String getTxtname() {return txtname;}
}
