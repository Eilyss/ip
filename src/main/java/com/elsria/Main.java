package com.elsria;

import java.io.IOException;
import java.nio.file.Path;

import com.elsria.commands.CommandHandler;
import com.elsria.commands.factory.CompleteCommandFactory;
import com.elsria.commands.parsers.CompleteCommandParser;
import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.task.ListLoadWrapper;
import com.elsria.ui.Cli;
import com.elsria.ui.Gui;
import com.elsria.ui.MainWindow;

import com.elsria.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
    public static final String LIST_STORAGE_DIRECTORY = "data";
    public static final String TO_DO_LIST_FILENAME = "ToDoList";
    private static final String NAME = "Elias";

    private CompleteCommandParser parser;
    private Storage storage;
    private ApplicationContext context;
    private boolean hasLoadError = false;

    private User user;
    private Chatbot chatbot;

    public void initialise() {
        Path path = Path.of(LIST_STORAGE_DIRECTORY,
                TO_DO_LIST_FILENAME);

        Storage storage = new Storage(path);
        ListLoadWrapper wrapper = storage.loadListFromStorage();

        ApplicationContext context =
                new ApplicationContext(NAME,
                        wrapper.getTaskList(),
                        storage);
        CompleteCommandParser parser = new CompleteCommandParser();
        CompleteCommandFactory factory = new CompleteCommandFactory(context);
        CommandHandler handler = new CommandHandler(parser, factory);

        this.chatbot = new Chatbot(NAME);
        this.chatbot.setContext(context);
        this.chatbot.setCommandHandler(handler);

        this.chatbot.setProfilePicture(new Image(this.getClass().getResourceAsStream("/images/Anon.png")));
        this.user = new User("User", new Image(this.getClass().getResourceAsStream("/images/Anon.png")));

    }

    @Override
    public void start(Stage stage) {
        this.initialise();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            Gui gui = new Gui(controller.getDialogueContainer());
            gui.setResponderProfilePicture(this.chatbot.getProfilePicture());
            gui.setSenderProfilePicture(this.user.getProfilePicture());
            controller.setCharacters(chatbot, user);
            controller.setGui(gui);
            this.chatbot.setUi(gui);
            this.chatbot.displayStartupMessage(this.hasLoadError);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
