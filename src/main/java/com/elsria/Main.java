package com.elsria;

import java.io.IOException;
import java.nio.file.Path;

import com.elsria.commands.CommandParser;
import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.task.ListLoadWrapper;
import com.elsria.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
    public static final String LIST_STORAGE_DIRECTORY = "data";
    public static final String TO_DO_LIST_FILENAME = "ToDoList";
    private static final String NAME = "Berdly";

    private CommandParser parser;
    private Storage storage;
    private ApplicationContext context;

    private User user;
    private Chatbot chatbot;

    public void initialise() {
        Path path = Path.of(LIST_STORAGE_DIRECTORY,
                TO_DO_LIST_FILENAME);
        this.parser = new CommandParser();
        this.storage = new Storage(path);

        ListLoadWrapper wrapper = this.storage.loadListFromStorage();

        this.context =
                new ApplicationContext(NAME,
                        wrapper.getTaskList(),
                        storage);
        this.chatbot = new Chatbot(NAME, parser, context);
        this.chatbot.setProfilePicture(new Image(this.getClass().getResourceAsStream("/images/Berdly.png")));
        this.user = new User("Ralsei", new Image(this.getClass().getResourceAsStream("/images/Hat_Ralsei.png")));

    }

    @Override
    public void start(Stage stage) {
        this.initialise();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCharacters(chatbot, user);
            stage.show();
            fxmlLoader.<MainWindow>getController().respond(this.chatbot.getStartupMessage(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
