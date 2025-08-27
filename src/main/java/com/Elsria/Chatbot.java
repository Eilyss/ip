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

    public void farewell() {
        this.ui.say("Okey dokey, see you soon!");
    }

    public void addTask(String task) {
        Task newTask = new ToDo(task);
        this.taskList.add(newTask);
        this.ui.say("added: " + task);
    }

    public void reciteList() {
        if (this.taskList.isEmpty()) {
            this.ui.say("Hmm... there's nothing in your list right now.");
            return;
        }

        this.ui.queueMessage("Here are the tasks on your list:");
        for (int i = 0; i < taskList.size(); i++) {
            this.ui.queueMessage(String.format("%d. %s", i + 1, taskList.get(i).toString()));
        }
        this.ui.sayMessages();
    }

    public void markTaskAsDone(int taskId) {
        if (!taskList.checkValidID(taskId)) {
            this.ui.say("Woah buddy that task does not exist!");
            return;
        }

        taskList.markTask(taskId);
        this.ui.queueMessage("Okay! I have marked that task as done.");
        this.ui.queueMessage(taskList.getTaskDescription(taskId));
        this.ui.sayMessages();
    }

    public void unmarkTaskAsDone(int taskId) {
        if (!taskList.checkValidID(taskId)) {
            this.ui.say("Woah buddy that task does not exist!");
            return;
        }

        taskList.unmarkTask(taskId);
        this.ui.queueMessage("Okay! That task is no longer marked as done");
        this.ui.queueMessage(taskList.getTaskDescription(taskId));
        this.ui.sayMessages();
    }


    public boolean parseUserInput(String userInput) {
        Command command = this.commandParser.parse(userInput);
        command.execute();

        return !this.commandParser.endProgram();
    }

}
