package com.elsria;

import javafx.scene.image.Image;

public class User {
    private String name;
    private Image profilePicture;

    public User(String name, Image profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return this.name;
    }

    public Image getProfilePicture() {
        return this.profilePicture;
    }
}
