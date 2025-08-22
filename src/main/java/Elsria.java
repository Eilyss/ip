import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Elsria {
    private static final String lineSeparator = "____________________________________________________________";
    private static final String name = "Elsria";

    public static ArrayList<String> parseString(String input) {
        ArrayList<String> solution = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '\n':
                    solution.add(sb.toString().strip());
                    sb.setLength(0);
                    break;
                default:
                    sb.append(c);
            }
        }
        if (sb.length() > 0) {
            solution.add(sb.toString().strip());
        }
        return solution;
    }

    public static void say(String message) {
        ArrayList<String> parsedMessage = parseString(message);
        System.out.println("\t" + lineSeparator);
        for (String s : parsedMessage) {
            System.out.println("\t" + s);
        }
        System.out.println("\t" + lineSeparator);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        say(String.format("Heya! It's me, %s!\nWhat do you wanna do today?", name));

        boolean running = true;
        String prompt = "";
        while (running) {
            prompt = sc.nextLine();
            switch (prompt.toLowerCase(Locale.ROOT)) {
                case "bye":
                    say("Okey dokey, see you soon!");
                    running = false;
                    break;
                default:
                    say(prompt);
            }
        }

    }
}
