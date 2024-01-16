package ua.lemoncat.zom100tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.lemoncat.zom100tasks.task.Status;
import ua.lemoncat.zom100tasks.task.Task;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTasksDTO {
    private String text;
    private Status status;

    public Task toObject(String ownerId) {
        return Task.builder()
                .ownerId(ownerId)
                .text(this.text)
                .status(this.status)
                .build();
    }
}
