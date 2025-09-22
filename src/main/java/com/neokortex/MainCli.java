package com.neokortex;

import java.nio.file.Path;
import java.util.Scanner;

import com.neokortex.commands.CommandHandler;
import com.neokortex.commands.factory.CompleteCommandFactory;
import com.neokortex.commands.parsers.CompleteCommandParser;
import com.neokortex.core.ApplicationContext;
import com.neokortex.core.Storage;
import com.neokortex.task.ListLoadWrapper;
import com.neokortex.ui.cli.Cli;
import com.neokortex.ui.Ui;

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
 * @see CompleteCommandParser
 * @see Storage
 */
public class MainCli {
    public static final String LIST_STORAGE_DIRECTORY = "data";
    public static final String TO_DO_LIST_FILENAME = "ToDoList";
    private static final String NAME = "Elsria";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path path = Path.of(LIST_STORAGE_DIRECTORY,
                TO_DO_LIST_FILENAME);


        Ui cli = new Cli();
        Storage storage = new Storage(path);
        ListLoadWrapper wrapper = storage.loadListFromStorage();

        ApplicationContext context =
                new ApplicationContext(NAME,
                        wrapper.getTaskList(),
                        storage);
        CompleteCommandParser parser = new CompleteCommandParser();
        CompleteCommandFactory factory = new CompleteCommandFactory(context);
        CommandHandler handler = new CommandHandler(parser, factory);

        Chatbot elsria = new Chatbot(NAME);
        elsria.setContext(context);
        elsria.setUi(cli);
        elsria.setCommandHandler(handler);
        elsria.displayStartupMessage(!wrapper.getFailedSerializations().isEmpty());

        boolean running = true;
        String prompt;
        while (running && sc.hasNextLine()) {
            prompt = sc.nextLine();
            elsria.interpret(prompt);
            elsria.respond();
            running = context.shouldKeepRunning();
        }
    }
}
