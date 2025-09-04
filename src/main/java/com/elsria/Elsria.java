package com.elsria;

import java.nio.file.Path;
import java.util.Scanner;

import com.elsria.commands.Command;
import com.elsria.commands.CommandParser;
import com.elsria.commands.CommandRequest;
import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
import com.elsria.task.ListLoadWrapper;

/**
 * Entry point for Elsria
 */
public class Elsria {
    public static final String LIST_STORAGE_DIRECTORY = "data";
    public static final String TO_DO_LIST_FILENAME = "ToDoList";
    private static final String name = "Elsria";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path path = Path.of(LIST_STORAGE_DIRECTORY,
                TO_DO_LIST_FILENAME);
        Chatbot elsria = new Chatbot(name);

        UiHandler uiHandler = new UiHandler();
        Storage storage = new Storage(path);
        ListLoadWrapper wrapper = storage.loadListFromStorage();
        CommandParser parser = new CommandParser();

        ApplicationContext context =
                new ApplicationContext(elsria.getName(),
                        uiHandler,
                        wrapper.getTaskList(),
                        storage);
        elsria.startupMessage(context.getUiHandler(),
                !wrapper.getFailedSerializations().isEmpty());

        boolean running = true;
        String prompt;
        while (running && sc.hasNextLine()) {
            prompt = sc.nextLine();
            CommandRequest request =
                    parser.getCommandType(prompt);
            Command command =
                    request.getCommandType().create(context, request);
            command.execute();
            running = !parser.endProgram();
        }
    }
}
