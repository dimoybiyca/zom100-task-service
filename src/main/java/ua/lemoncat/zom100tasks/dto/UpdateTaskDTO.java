package ua.lemoncat.zom100tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lemoncat.zom100tasks.task.Task;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDTO {
    private UUID id;
    private String text;

    public Task toEntity(String ownerId, Task oldTask) {
        return Task.builder()
                .id(this.id)
                .text(this.text)
                .ownerId(ownerId)
                .status(oldTask.getStatus())
                .build();
    }
}
