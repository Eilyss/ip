package com.elsria.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogueBox extends HBox {
    @FXML
    private Label dialogue;
    @FXML
    private ImageView displayPicture;

    public DialogueBox(String text, Image profilePicture) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogue.setText(text);

        if (profilePicture == null) {
            this.getChildren().remove(displayPicture);
        } else {
            displayPicture.setImage(profilePicture);
        }
    }

    public DialogueBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogue.setText(text);
        this.getChildren().remove(displayPicture);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogueBox createSenderDialogue(String s, Image i) {
        return new DialogueBox(s, i);
    }

    public static DialogueBox createResponseDialogue(String s, Image i) {
        DialogueBox db = new DialogueBox(s, i);
        db.flip();
        return db;
    }
}
