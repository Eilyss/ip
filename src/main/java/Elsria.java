import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Elsria {
    private static final String lineSeparator = "____________________________________________________________";
    private static final String name = "Elsria";
    private static final TaskList taskList = new TaskList();

    public static void sayRaw(String message) {
        System.out.println("\t" + lineSeparator);
        System.out.println("\t" + message);
        System.out.println("\t" + lineSeparator);
    }

    public static void say(String message) {
        say(parseString(message));
    }

    public static ArrayList<String> parseString(String input) {
        ArrayList<String> solution = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '\n') {
                solution.add(sb.toString().strip());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (!sb.isEmpty()) {
            solution.add(sb.toString().strip());
        }
        return solution;
    }

    public static void say(ArrayList<String> messages) {
        System.out.println("\t" + lineSeparator);
        for (String s : messages) {
            System.out.println("\t" + s);
        }
        System.out.println("\t" + lineSeparator);
    }

    public static void addToList(Task task) {
        taskList.add(task);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        say(String.format("Heya! It's me, %s!\nWhat do you wanna do today?", name));

        boolean running = true;
        String prompt;
        while (running) {
            prompt = sc.nextLine();
            switch (prompt.toLowerCase(Locale.ROOT)) {
                case "bye":
                    say("Okey dokey, see you soon!");
                    running = false;
                    break;
                case "list":
                    if (taskList.isEmpty()) {
                        say("Hmm... there's nothing in your list right now.");
                    } else {
                        say("Here are the tasks on your list:\n" + taskList.toString());
                    }
                    break;
                default:
                    String[] parsedPrompt = prompt.split(" ");
                    switch (parsedPrompt[0]) {
                        case "mark":
                            int toMark = Integer.parseInt(parsedPrompt[1]);
                            if (toMark < 0 || toMark >= taskList.size()) {
                                say("Woah buddy that task does not exist!");
                                break;
                            }
                            taskList.markTask(toMark);
                            say(String.format(
                                    "Okay! I have marked that task as done.\n %s",
                                    taskList.getTaskDescription(toMark))
                            );
                            break;
                        case "unmark":
                            int toUnmark = Integer.parseInt(parsedPrompt[1]);
                            if (toUnmark < 0 || toUnmark >= taskList.size()) {
                                say("Woah buddy that task does not exist!");
                                break;
                            }
                            taskList.unmarkTask(toUnmark);
                            say(String.format(
                                    "Okay! That task is no longer marked as done\n %s",
                                    taskList.getTaskDescription(toUnmark))
                            );
                            break;
                        default:
                            say("added: " + prompt);
                            addToList(new Task(prompt));
                    }
            }
        }

    }
}
