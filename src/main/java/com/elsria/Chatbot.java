package com.elsria;

import com.elsria.commands.CommandHandler;
import com.elsria.commands.Response;
import com.elsria.core.ApplicationContext;
import com.elsria.ui.Ui;

import javafx.scene.image.Image;

import java.util.regex.Pattern;

/**
 * Represents a conversational chatbot that provides user interaction through responses.
 * <p>
 * The {@code Chatbot} serves as the primary interface for user interaction,
 * handling startup messages, and providing a personalized experience. It
 * has a name for identification and personalization.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Maintaining chatbot identity</li>
 *   <li>Handling startup messages</li>
 *   <li>Providing friendly responses to user input</li>
 * </ul>
 *
 * @see Ui
 */
public class Chatbot {
    private final String name;
    private CommandHandler handler;
    private ApplicationContext context;
    private Ui ui;
    private Image profilePicture;
    private DialogueMap dialogueMap;
    private String[] pendingResponses;

    /**
     * Constructs a new Chatbot instance with the specified name.
     *
     * @param name the name of this chatbot. It is used in personalized messages.
     */
    public Chatbot(String name) {
        this.name = name;
        this.dialogueMap = new DialogueMap();
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public void setCommandHandler(CommandHandler handler) {
        this.handler = handler;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public void setDialogueMap(DialogueMap dialogueMap) {
        this.dialogueMap = dialogueMap;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }
    /**
     * Returns the name of the chatbot.
     *
     * @return the chatbot's name, as provided during construction.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Displays startup messages to the user through the provided UI handler.
     * <p>
     * This method greets the user and notifies the use of any data loading
     * issues.
     * </p>
     *
     * <p><b>Message Sequence:</b></p>
     * <ol>
     *   <li>Greets the user</li>
     *   <li>Display special message upon load error</li>
     *   <li>Prompts user for action</li>
     * </ol>
     *
     * @param hasLoadError indicates whether data loading encountered errors.
     *                     If true, a specialised error message will be displayed.
     *
     * @throws NullPointerException if uiHandler is null
     *
     * @see Ui
     */
    public void displayStartupMessage(boolean hasLoadError) {
        if (hasLoadError) {
            ui.respond(this.getDialogueFromDirective(DialoguePath.STARTUP_FAILURE));
        } else {
            ui.respond(this.getDialogueFromDirective(DialoguePath.GREET));
        }
    }

    /**
     * todo
     * @param input
     * @return
     */
    public void interpret(String input) {
        Response handlerResponse = this.handler.interpret(input);
        this.pendingResponses = this.getDialogueFromDirective(handlerResponse.getDirective(), handlerResponse.getAttachedResults());
    }

    public void respond() {
        this.ui.respond(pendingResponses);
        pendingResponses = null;
    }

    private String[] getDialogueFromDirective(DialoguePath directive, String... arguments) {
        String base = this.dialogueMap.getDialogueFromDirective(directive);
        String formatted = makeSubstitutions(base, arguments);
        return new String[] { formatted };
    }

    private String makeSubstitutions(String input, String... arguments) {
        Pattern curlyBraces = Pattern.compile("(?<!\\\\)\\{([^{}]*)\\}");
        String output = curlyBraces.matcher(input).replaceAll(match -> {
            String content = match.group(1);
            String[] parts = content.split(":");
            String key = parts[0];

            return switch (key) {
            case "r" -> arguments[Integer.parseInt(parts[1])];
            case "name" -> this.name;
            default -> content;
            };
        });

        return output;
    }
}
