package com.Elsria;

import com.Elsria.Commands.FarewellCommand;

public class Chatbot {
    private final String name;
    private final UIHandler ui;
    private final TaskList taskList;
    private final CommandParser commandParser;

    public Chatbot(String name) {
        this.name = name;
        this.ui = new UIHandler();
        this.taskList = new TaskList();
        this.commandParser = new CommandParser(name, this.ui, this.taskList);
    }

    public void greet() {
        this.ui.queueMessage(String.format("Heya! It's me, %s!", name));
        this.ui.queueMessage("What do you wanna do today?");
        this.ui.sayMessages();
    }

    public boolean parseUserInput(String userInput) {
        Command command = this.commandParser.parse(userInput);
        command.execute();

        return !this.commandParser.endProgram();
    }

}
