package com.elsria;

import com.elsria.commands.Command;
import com.elsria.commands.CommandParser;
import com.elsria.commands.CommandRequest;
import com.elsria.core.ApplicationContext;
import com.elsria.core.UiHandler;

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
 * @see UiHandler
 */
public class Chatbot {
    private final String name;
    private CommandParser parser;

    /**
     * Constructs a new Chatbot instance with the specified name.
     *
     * @param name the name of this chatbot. It is used in personalized messages.
     */
    public Chatbot(String name, CommandParser parser) {
        this.name = name;
        this.parser = parser;
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
     * @param uiHandler the user interface handler used to display messages.
     * @param hasLoadError indicates whether data loading encountered errors.
     *                     If true, a specialised error message will be displayed.
     *
     * @throws NullPointerException if uiHandler is null
     *
     * @see UiHandler#queueMessage(String)
     * @see UiHandler#sayMessages()
     */
    public void startupMessage(UiHandler uiHandler, boolean hasLoadError) {
        uiHandler.queueMessage(String.format("Heya! It's me, %s!", name));
        if (hasLoadError) {
            uiHandler.queueMessage("Hmm... there seems to be an error with loading your data.");
            uiHandler.queueMessage("Could you check it out please?");
            uiHandler.queueMessage("In any case, welcome!");
            uiHandler.sayMessages();
        }
        uiHandler.queueMessage("What do you wanna do today?");
        uiHandler.sayMessages();
    }

    /**
     * todo
     * @param context
     * @param input
     * @return
     */
    public boolean interpret(ApplicationContext context, String input) {
        CommandRequest request = this.parser.getCommandType(input);
        Command command = request.getCommandType().create(context, request);
        command.execute();
        return context.shouldKeepRunning();
    }
}
