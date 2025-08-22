public class ChatBot {
    private static final String lineSeparator = "____________________________________________________________\n";
    private final String name;

    public ChatBot(String name) {
        this.name = name;
    }

    public String introduction() {
        return String.format("""
            Hello! I'm %s.
            What can I do for you today?
        """, this.name);
    }

    public void say(String message) {
        System.out.println(message + "\n" + lineSeparator);
    }
}
