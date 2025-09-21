package com.elsria;

import javafx.scene.image.Image;

public class User {
    private String name;
    private Image profilePicture;

    public User(String name, Image profilePicturePath) {
        this.name = name;
        this.profilePicture = profilePicturePath;
    }

    public String getName() {
        return this.name;
    }

    public Image getProfilePicture() {
        return this.profilePicture;
    }
}
