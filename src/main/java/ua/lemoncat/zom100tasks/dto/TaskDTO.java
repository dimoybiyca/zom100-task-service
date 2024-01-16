package ua.lemoncat.zom100tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.lemoncat.zom100tasks.task.Status;
import ua.lemoncat.zom100tasks.task.Task;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private UUID id;
    private String text;
    private Status status;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.text = task.getText();
        this.status = task.getStatus();
    }
}
