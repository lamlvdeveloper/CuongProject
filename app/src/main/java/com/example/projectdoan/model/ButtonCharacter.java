package com.example.projectdoan.model;

public class ButtonCharacter {
    private String name;
    private boolean isShow;

    public ButtonCharacter(String name, boolean isShow) {
        this.name = name;
        this.isShow = isShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
