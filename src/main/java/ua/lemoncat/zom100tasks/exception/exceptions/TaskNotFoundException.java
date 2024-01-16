package ua.lemoncat.zom100tasks.exception.exceptions;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends RuntimeException{

    private final String causeMessage;

    public TaskNotFoundException(String message) {
        super("task-not-found");
        this.causeMessage = message;
    }
}
