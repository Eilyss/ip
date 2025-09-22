package com.neokortex.ui.gui;

import com.neokortex.ui.Ui;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Gui extends Ui {
    private VBox root;
    private Image senderProfilePicture;
    private Image responderProfilePicture;

    public Gui(VBox root) {
        this.root = root;
    }

    public void setSenderProfilePicture(Image senderProfilePicture) {
        this.senderProfilePicture = senderProfilePicture;
    }

    public void setResponderProfilePicture(Image responderProfilePicture) {
        this.responderProfilePicture = responderProfilePicture;
    }

    @Override
    public void say(String message) {
        this.root.getChildren().add(DialogueBox.createSenderDialogue(message, this.senderProfilePicture));
    }

    @Override
    public void respond(String message) {
        this.root.getChildren().add(DialogueBox.createResponseDialogue(message, this.responderProfilePicture));
    }

    @Override
    public void respond(String[] messages) {
        for (String message : messages) {
            this.respond(message);
        }
    }
}
