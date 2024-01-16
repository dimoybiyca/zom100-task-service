package ua.lemoncat.zom100tasks.exception.exceptions;

import lombok.Getter;
import ua.lemoncat.zom100tasks.task.Status;

@Getter
public class TaskAlreadyHaveStatusException extends RuntimeException{

    private final String causeMessage;

    public TaskAlreadyHaveStatusException(String message) {
        super(message.contains(Status.COMPLETED.toString())
                ? "task-is-already-completed"
                : "task-is-already-in-progress"
        );

        this.causeMessage = message;
    }
}
