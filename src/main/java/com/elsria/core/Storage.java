package com.elsria.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.elsria.task.ListLoadWrapper;
import com.elsria.task.Task;
import com.elsria.task.TaskList;

public class Storage {
    private final Path filePath;

    public Storage(final Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return this.filePath;
    }

    public ListLoadWrapper loadListFromStorage() {
        if (!Files.exists(this.filePath)) {
            return null; // File doesn't exist
        }

        TaskList list = new TaskList();
        List<String> failedLines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Task.deserialize(line);
                    list.add(task);
                } catch (Exception e) {
                    failedLines.add(line);
                }
            }
        } catch (IOException e) {
            return null;
        }

        return new ListLoadWrapper(list, failedLines);
    }

    public boolean saveListToStorage(TaskList taskList) {
        System.out.println("Writing to file...");
        try {
            Files.createDirectories(filePath.getParent());

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Task task : taskList) {
                    writer.write(task.serialize());
                    writer.newLine();
                }
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
