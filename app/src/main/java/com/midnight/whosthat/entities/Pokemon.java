package com.midnight.whosthat.entities;

public class Pokemon {
    private String name;
    private String imageUrl;

    public Pokemon(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
