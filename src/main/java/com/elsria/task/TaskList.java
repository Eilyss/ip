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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < super.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, super.get(i)));
        }
        return sb.toString();
    }
}
