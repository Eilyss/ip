import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Elsria {
    private static final String name = "Elsria";
    private static final TaskList taskList = new TaskList();

    public static void addToList(Task task) {
        taskList.add(task);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Chatbot Elsria = new Chatbot(name);
        Elsria.queueMessage(String.format("Heya! It's me, %s!", name));
        Elsria.queueMessage("What do you wanna do today?");
        Elsria.sayMessages();

        boolean running = true;
        String prompt;
        while (running) {
            prompt = sc.nextLine();
            switch (prompt.toLowerCase(Locale.ROOT)) {
                case "bye":
                    Elsria.say("Okey dokey, see you soon!");
                    running = false;
                    break;
                case "list":
                    if (taskList.isEmpty()) {
                        Elsria.say("Hmm... there's nothing in your list right now.");
                    } else {
                        Elsria.queueMessage("Here are the tasks on your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            Elsria.queueMessage(String.format("%d. %s", i + 1, taskList.get(i).toString()));
                        }
                        Elsria.sayMessages();
                    }
                    break;
                default:
                    String[] parsedPrompt = prompt.split(" ");
                    switch (parsedPrompt[0]) {
                        case "mark":
                            int toMark = Integer.parseInt(parsedPrompt[1]) - 1;
                            if (toMark < 0 || toMark >= taskList.size()) {
                                Elsria.say("Woah buddy that task does not exist!");
                                break;
                            }
                            taskList.markTask(toMark);
                            Elsria.queueMessage("Okay! I have marked that task as done.");
                            Elsria.queueMessage(taskList.getTaskDescription(toMark));
                            Elsria.sayMessages();
                            break;
                        case "unmark":
                            int toUnmark = Integer.parseInt(parsedPrompt[1]) - 1;
                            if (toUnmark < 0 || toUnmark >= taskList.size()) {
                                Elsria.say("Woah buddy that task does not exist!");
                                break;
                            }
                            taskList.unmarkTask(toUnmark);
                            Elsria.queueMessage("Okay! That task is no longer marked as done");
                            Elsria.queueMessage(taskList.getTaskDescription(toUnmark));
                            Elsria.sayMessages();
                            break;
                        default:
                            Elsria.say("added: " + prompt);
                            addToList(new Task(prompt));
                    }
            }
        }
    }
}
