package com.neokortex;

import java.io.IOException;
import java.nio.file.Path;

import com.neokortex.commands.CommandHandler;
import com.neokortex.commands.factory.CompleteCommandFactory;
import com.neokortex.commands.parsers.CompleteCommandParser;
import com.neokortex.core.ApplicationContext;
import com.neokortex.core.Storage;
import com.neokortex.task.ListLoadWrapper;
import com.neokortex.ui.Gui;
import com.neokortex.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
    public static final String LIST_STORAGE_DIRECTORY = "data";
    public static final String TO_DO_LIST_FILENAME = "ToDoList";
    private static final String NAME = "ACE";

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

        this.chatbot.setProfilePicture(new Image(this.getClass().getResourceAsStream("/images/Ace.png")));
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
            stage.setTitle("NeoKortex");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/NeoKortex Icon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
