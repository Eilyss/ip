package com.elsria;

import java.util.Scanner;

public class Elsria {
    private static final String name = "Elsria";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Chatbot Elsria = new Chatbot(name);
        Elsria.startup();

        boolean running = true;
        String prompt;
        while (running && sc.hasNextLine()) {
            prompt = sc.nextLine();
            running = Elsria.parseUserInput(prompt);
        }
    }
}
