package com.elsria.task;

import com.elsria.exceptions.InvalidTaskSerializationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public boolean checkValidID(int id) {
        return id >= 0 && id < super.size();
    }

    public void markTask(int id) {
        super.get(id).mark();
    }

    public void unmarkTask(int id) {
        super.get(id).unmark();
    }

    public String getTaskDescription(int id) {
        return super.get(id).toString();
    }

    public boolean writeToFile(String directory, String filename) {
        System.out.println("Writing to file...");
        try {
            File dir = new File(directory);
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
            for (Task task : this) {
                writer.write(task.serialize());
                writer.newLine();
            }

            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static ListLoadWrapper readFromFile(String directory, String filename) {
        System.out.println("Reading from file...");
        try {
            File dir = new File(directory);
            if (!dir.exists()) {
                return null;
            }

            File listFile = new File(dir, filename);
            if (!listFile.exists()) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new FileReader(listFile));

            String line;
            TaskList list = new TaskList();
            ArrayList<String> failedLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                Task nextTask;
                try {
                    nextTask = Task.deserialize(line);
                    list.add(nextTask);
                } catch (Exception e) {
                    failedLines.add(line);
                }
            }

            reader.close();
            return new ListLoadWrapper(list, failedLines);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < super.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, super.get(i)));
        }
        return sb.toString();
    }
}
