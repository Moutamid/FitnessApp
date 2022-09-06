package com.example.fitnessapp.Model;

import java.io.Serializable;

public class Recipes implements Serializable {

    private String name;
    private String description;
    private String image;

    public Recipes(){

    }

    public Recipes(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Recipes(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
