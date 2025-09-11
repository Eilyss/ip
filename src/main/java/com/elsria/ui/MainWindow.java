package com.elsria.ui;

import com.elsria.Chatbot;
import com.elsria.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Chatbot respondent;
    private User user;

    @FXML
    public void initialise() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
    }

    public void setCharacters(Chatbot respondent, User user) {
        this.respondent = respondent;
        this.user = user;
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.respondent.interpret(input);
        this.dialogueContainer.getChildren().addAll(
                DialogueBox.createSenderDialogue(input, this.user.getProfilePicture()),
                DialogueBox.createResponseDialogue(response, this.respondent.getProfilePicture())
        );
        this.userInput.clear();
    }

    public void respond(String response) {
        this.dialogueContainer.getChildren().addAll(
                DialogueBox.createResponseDialogue(response, this.respondent.getProfilePicture())
        );
    }
}
