import java.util.Arrays;
import java.util.Set;

public enum Command {
    greet(0, "hello"),
    help(1, "help"),
    list(0, "list"),
    add(1, "add"),
    remove(1, "remove"),
    mark(1, "mark"),
    unmark(1, "unmark"),
    farewell(0, "bye"),
    invalid(0);

    private final Set<String> alias;
    private int argCount;

    Command(int argCount, String... commands) {
        this.argCount = argCount;
        this.alias = Set.of(commands);
    }

    public static Command interpretCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return invalid;
        }

        String cleanCommand = command.toLowerCase().trim();

        return Arrays.stream(values())
                .filter(cmd -> cmd.alias.contains(cleanCommand))
                .findFirst()
                .orElse(invalid);
    }
}