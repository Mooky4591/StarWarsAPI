package com.example.starwarsapi;

public class RecyclerItem {

    private String name, height, mass, birthyear, eyecolor;

    public RecyclerItem(String name, String height, String mass, String birthyear, String eyecolor){
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.birthyear = birthyear;
        this.eyecolor = eyecolor;

    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getBirthYear() {
        return birthyear;
    }

    public String getEyecolor() { return eyecolor; }

}
