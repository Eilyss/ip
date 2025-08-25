package com.Elsria;

import java.util.Scanner;

public class Elsria {
    private static final String name = "com.Elsria.Elsria";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Chatbot Elsria = new Chatbot(name);
        Elsria.greet();

        boolean running = true;
        String prompt;
        while (running) {
            prompt = sc.nextLine();
            running = Elsria.parseUserInput(prompt);
        }
    }
}
