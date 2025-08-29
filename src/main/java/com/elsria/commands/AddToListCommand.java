package com.elsria.commands;

import com.elsria.task.Task;
import com.elsria.task.TaskList;
import com.elsria.UiHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AddToListCommand extends Command {
    private UiHandler ui;
    private TaskList taskList;
    private Task task;
    protected String errorMessage;

    public AddToListCommand(UiHandler uiHandler, TaskList taskList, String rawArguments) {
        this.taskList = taskList;
        this.ui = uiHandler;
        try {
            this.task = createTask(rawArguments);
        } catch (IllegalArgumentException e) {
            this.errorMessage = "Woah, something went wrong...";
        }
    }


    @Override
    public void execute() {
        if (task == null) {
            this.ui.say(this.errorMessage);
            return;
        }
        this.taskList.add(this.task);
        this.ui.queueMessage("added: " + this.task);
        if (!this.writeToFile("ToDoList")) {
            this.ui.queueMessage("Woah, hold on...");
            this.ui.queueMessage("I seem to be unable to store this list on the drive");
            this.ui.queueMessage("Could you run the Save command?");
        }
        this.ui.sayMessages();

    }

    public boolean writeToFile(String filename) {
        System.out.println("Writing to file...");
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                boolean created = dir.mkdirs();
                if (!created) {
                    return false;
                }
            }

            File listFile = new File(dir, filename);
            if (!listFile.exists()) {
                listFile.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(listFile));
            for (Task task : this.taskList) {
                writer.write(task.serialize());
                writer.newLine();
            }

            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public abstract Task createTask(String args);
}
