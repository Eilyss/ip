import java.util.ArrayList;
import java.util.HashMap;

public class Chatbot {
    private static final String lineSeparator = "____________________________________________________________";
    private String name;
    private ArrayList<String> messages;
    private TaskList tasks;

    public Chatbot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void queueMessage(String message) {
        this.messages.add(message);
    }

    public void resetMessages() {
        this.messages.clear();
    }

    public void sayMessages() {
        System.out.println("\t" + lineSeparator);
        for (String s : this.messages) {
            System.out.println("\t" + s);
        }
        System.out.println("\t" + lineSeparator);
        this.resetMessages();
    }

    public void say(String message) {
        System.out.println("\t" + lineSeparator);
        System.out.println("\t" + message);
        System.out.println("\t" + lineSeparator);
    }

}
