package com.neokortex.ui;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class DialogueBox extends HBox {
    @FXML
    private Label dialogue;
    @FXML
    private Pane imageContainer;
    @FXML
    private ImageView displayPicture;
    @FXML
    private StackPane messageBox;

    private static final String RESPONSE_COLOR = "#DAE5E3";
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
            this.displayPicture.setImage(profilePicture);

            Circle clip = new Circle();
            clip.radiusProperty().bind(this.imageContainer.widthProperty().divide(2));
            clip.centerXProperty().bind(this.imageContainer.widthProperty().divide(2));
            clip.centerYProperty().bind(this.imageContainer.heightProperty().divide(2));

            this.displayPicture.setClip(clip);
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

    private void setColor(String color) {
        messageBox.setStyle(String.format("-fx-background-color: %s; -fx-background-radius: 20px;", RESPONSE_COLOR));
    }

    public static DialogueBox createSenderDialogue(String text, Image profilePicture) {
        return new DialogueBox(text, profilePicture);
    }

    public static DialogueBox createResponseDialogue(String text, Image profilePicture) {
        DialogueBox db = new DialogueBox(text, profilePicture);
        db.flip();
        db.setColor(RESPONSE_COLOR);
        return db;
    }
}
