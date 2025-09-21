package com.elsria.commands;

import com.elsria.DialoguePath;

public class Response {
    private DialoguePath directive;
    private ResponseStatus responseStatus;
    private String[] attachedResults;

    public Response(DialoguePath directive, ResponseStatus responseType) {
        this.directive = directive;
        this.responseStatus = responseType;
    }

    public void attachResults(String[] additionalResults) {
        this.attachedResults = additionalResults;
    }

    public String[] getAttachedResults() {
        return this.attachedResults;
    }

    public DialoguePath getDirective() {
        return this.directive;
    }

    public boolean isSuccessful() {
        return this.responseStatus == ResponseStatus.SUCCESS;
    }

    public ResponseStatus getStatus() {
        return this.responseStatus;
    }

}
