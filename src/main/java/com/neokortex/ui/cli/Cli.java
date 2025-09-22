package com.neokortex.ui.cli;

import com.neokortex.ui.Ui;

/**
 * To Be Updated...
 */
public class Cli extends Ui {
    private static final String BORDER =
            "____________________________________________________________";
    private int maxCharCountPerLine;

    public Cli() {
        this.maxCharCountPerLine = 60; // default char count per line
    }

    public Cli(int maxCharCountPerLine) {
        this.maxCharCountPerLine = maxCharCountPerLine;
    }

    @Override
    public void say(String message) {

    }

    @Override
    public void respond(String message) {
        String[] messageParts = message.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        int charsInLine = 0;
        boolean lineEmpty = true;

        System.out.println("\t" + BORDER);
        for (String s : messageParts) {
            String[] tokens = s.strip().split("\\s+");
            sb = new StringBuilder();
            charsInLine = 0;
            for (String token : tokens) {
                charsInLine += token.length();
                if (!lineEmpty && charsInLine > maxCharCountPerLine) {
                    System.out.println("\t  " + sb);
                    sb = new StringBuilder();
                    charsInLine = token.length();
                    lineEmpty = true;
                }
                sb.append(token);
                sb.append(" ");
                charsInLine++;
                lineEmpty = false;
            }
            if (!lineEmpty) {
                System.out.println("\t  " + sb);
            }
        }
        System.out.println("\t" + BORDER);
    }

    @Override
    public void respond(String[] messages) {
        for (String message : messages) {
            respond(message);
        }
    }

}
