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

    private Gui gui;
    private Chatbot chatbot;
    private User user;

    @FXML
    public void initialise() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
    }

    public void setCharacters(Chatbot chatbot, User user) {
        this.chatbot = chatbot;
        this.user = user;
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        gui.say(input);
        this.chatbot.interpret(input);
        this.chatbot.respond();
        this.userInput.clear();
    }

    public VBox getDialogueContainer() {
        return this.dialogueContainer;
    }
}
