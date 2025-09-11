package com.elsria;

import java.nio.file.Path;
import java.util.Scanner;

import com.elsria.commands.CommandParser;
import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
import com.elsria.task.ListLoadWrapper;

/**
 * Main entry point and application launcher for the Elsria task management system.
 * <p>
 * Elsria is a chatbot that helps users manage their tasks with persistent storage
 * and a command-line interface for task management.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li>Interactive command-based task management</li>
 *   <li>Persistent storage of tasks to local files</li>
 *   <li>Support for different task types (todo, deadline, event)</li>
 *   <li>User-friendly console interface</li>
 * </ul>
 *
 * Credit: JavaDoc was written with guidance from generative AI
 *
 * @see ApplicationContext
 * @see CommandParser
 * @see Storage
 * @see UiHandler
 */
public class Elsria {
    public static final String LIST_STORAGE_DIRECTORY = "data";
    public static final String TO_DO_LIST_FILENAME = "ToDoList";
    private static final String name = "Elsria";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path path = Path.of(LIST_STORAGE_DIRECTORY,
                TO_DO_LIST_FILENAME);
        CommandParser parser = new CommandParser();

        UiHandler uiHandler = new UiHandler();
        Storage storage = new Storage(path);
        ListLoadWrapper wrapper = storage.loadListFromStorage();

        ApplicationContext context =
                new ApplicationContext(name,
                        wrapper.getTaskList(),
                        storage);
        Chatbot elsria = new Chatbot(name, parser, context);
        uiHandler.say(elsria.getStartupMessage(
                !wrapper.getFailedSerializations().isEmpty()));

        boolean running = true;
        String prompt;
        while (running && sc.hasNextLine()) {
            prompt = sc.nextLine();
            String response = elsria.interpret(prompt);
            uiHandler.say(response);
            running = context.shouldKeepRunning();
        }
    }
}
