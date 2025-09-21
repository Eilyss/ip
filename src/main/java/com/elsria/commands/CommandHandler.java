package com.elsria.commands;

import com.elsria.DialoguePath;
import com.elsria.commands.factory.CompleteCommandFactory;
import com.elsria.commands.factory.FactoryResponse;
import com.elsria.commands.impl.Command;
import com.elsria.commands.impl.CommandRequest;
import com.elsria.commands.impl.CommandResponse;
import com.elsria.commands.parsers.CompleteCommandParser;
import com.elsria.commands.parsers.ParserResponse;

public class CommandHandler {
    private enum ProcessingStage {
        IDLE,
        PREPROCESSING,
        INITIALIZATION,
        EXECUTION,
        COMPLETION
    }

    private CompleteCommandParser parser;
    private CompleteCommandFactory factory;
    private CommandRequest currentRequest;
    private Command currentCommand;
    private CommandResponse latestCommandResponse;
    private ProcessingStage currentStage;

    public CommandHandler(CompleteCommandParser parser, CompleteCommandFactory factory) {
        this.currentStage = ProcessingStage.IDLE;
        this.parser = parser;
        this.factory = factory;
    }

    public void setParser(CompleteCommandParser parser) {
        this.parser = parser;
    }

    public void setFactory(CompleteCommandFactory factory) {
        this.factory = factory;
    }

    public Response interpret(String input) {
        return switch (this.currentStage) {
        case IDLE -> handleIdleStage(input);
        case PREPROCESSING -> handlePreprocessingStage(input);
        case INITIALIZATION -> handleInitializationStage(this.currentRequest, input);
        case EXECUTION -> handleExecutionStage(this.currentCommand, input);
        case COMPLETION -> handleCompletionStage(this.latestCommandResponse, input);
        default -> throw new IllegalStateException("Unexpected value: " + this.currentStage);
        };
    }

    private Response handleIdleStage(String input) {
        if (!this.parser.canParse(input)) {
            return this.handleTotalFailure();
        }
        this.currentStage = ProcessingStage.PREPROCESSING;
        return this.handlePreprocessingStage(input);

    }

    private Response handlePreprocessingStage(String input) {
        ParserResponse response = this.parser.parse(input);

        if (response.getStatus() == ResponseStatus.TOTAL_FAILURE
                || response.getStatus() == ResponseStatus.CATASTROPHIC_FAILURE) {
            return this.handleTotalFailure(response);
        }

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.currentRequest = response.getResult();
            this.currentStage = ProcessingStage.INITIALIZATION;
            return handleInitializationStage(this.currentRequest, input);
        }

        return response;
    }

    private Response handleInitializationStage(CommandRequest request, String input) {
        FactoryResponse response = this.factory.handle(request);

        if (response.getStatus() == ResponseStatus.TOTAL_FAILURE) {
            return this.handleTotalFailure(response);
        }

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.currentCommand = response.getResult();
            this.currentStage = ProcessingStage.EXECUTION;
            return handleExecutionStage(this.currentCommand, input);
        }

        return response;
    }

    private Response handleExecutionStage(Command command, String input) {
        CommandResponse response = command.execute();

        if (response.getStatus() == ResponseStatus.TOTAL_FAILURE) {
            return this.handleTotalFailure(response);
        }

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.currentStage = ProcessingStage.COMPLETION;
            return handleCompletionStage(response, input);
        }

        return response;
    }

    private Response handleCompletionStage(CommandResponse response, String input) {
        if (response.getStatus() == ResponseStatus.TOTAL_FAILURE) {
            return this.handleTotalFailure(response);
        }

        if (response.getStatus() == ResponseStatus.SUCCESS || response.getStatus() == ResponseStatus.EXIT_PROGRAM) {
            this.currentStage = ProcessingStage.IDLE;
            return response;
        }

        return response;
    }

    private Response handleTotalFailure(Response response) {
        this.currentStage = ProcessingStage.IDLE;
        return response;
    }

    private Response handleTotalFailure() {
        return this.handleTotalFailure(new Response(DialoguePath.GENERIC_FAILURE, ResponseStatus.TOTAL_FAILURE));
    }
}
