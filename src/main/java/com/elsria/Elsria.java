package com.elsria;

import com.elsria.commands.Command;
import com.elsria.commands.CommandParser;
import com.elsria.commands.CommandRequest;
import com.elsria.core.ApplicationContext;
import com.elsria.task.ListLoadWrapper;
import com.elsria.task.TaskList;

import java.util.Scanner;

public class Elsria {
    private static final String name = "Elsria";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Chatbot Elsria = new Chatbot(name);
        UiHandler uiHandler = new UiHandler();
        ListLoadWrapper wrapper = TaskList.readFromFile(Constants.LIST_STORAGE_DIRECTORY, Constants.TO_DO_LIST_FILENAME);
        CommandParser parser = new CommandParser();

        ApplicationContext context = new ApplicationContext(Elsria.getName(), uiHandler, wrapper.getTaskList(), null);
        Elsria.startupMessage(context.getUIHandler(), !wrapper.getFailedSerializations().isEmpty());

        boolean running = true;
        String prompt;
        while (running && sc.hasNextLine()) {
            prompt = sc.nextLine();
            CommandRequest request = parser.getCommandType(prompt);
            Command command = request.getCommandType().create(context, request);
            command.execute();
            running = !parser.endProgram();
        }
    }
}
