package ua.lemoncat.zom100tasks.dto;

import lombok.Data;
import ua.lemoncat.zom100tasks.task.Status;
import ua.lemoncat.zom100tasks.task.Task;

@Data
public class CreateTaskDTO {
    private String text;

    public Task toEntity(String ownerId) {
        return Task.builder()
                .ownerId(ownerId)
                .text(this.text)
                .status(Status.IN_PROGRESS)
                .build();
    }
}
