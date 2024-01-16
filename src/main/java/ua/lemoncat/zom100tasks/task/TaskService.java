package ua.lemoncat.zom100tasks.task;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lemoncat.zom100tasks.dto.CreateTaskDTO;
import ua.lemoncat.zom100tasks.dto.SaveTasksDTO;
import ua.lemoncat.zom100tasks.dto.UpdateTaskDTO;
import ua.lemoncat.zom100tasks.exception.exceptions.TaskAlreadyHaveStatusException;
import ua.lemoncat.zom100tasks.exception.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAll(String userId) {
        return taskRepository.getAllByOwnerIdOrderByCreationDate(userId);
    }

    public List<Task> create(CreateTaskDTO dto, String userId) {
        taskRepository.save(dto.toEntity(userId));

        return this.getAll(userId);
    }

    public List<Task> update(UpdateTaskDTO dto, String userId) {
        taskRepository.getByIdAndOwnerId(dto.getId(), userId)
                .map(task -> taskRepository.save(dto.toEntity(userId, task)))
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task with id %s not found", dto.getId())));

        return this.getAll(userId);
    }

    public List<Task> delete(UUID taskId, String userId) {
        taskRepository.deleteByIdAndOwnerId(taskId, userId);
        List<Task> list = getAll(userId);
        if(list.stream().noneMatch(task -> task.getId() == taskId)) {
            return list;
        } else {
            throw new TaskNotFoundException(String.format("Task with id %s not found", taskId));
        }
    }

    public List<Task> changeStatus(UUID taskId, String userId, Status status) {
        return taskRepository.getByIdAndOwnerId(taskId, userId)
                .map((Task task) -> {
                    if(task.getStatus().equals(status)) {
                        throw new TaskAlreadyHaveStatusException("Task already have status " + status);
                    } else {
                        task.setStatus(status);
                        taskRepository.save(task);
                        return this.getAll(userId);
                    }
                })
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task with id %s not found", taskId)));
    }

    public List<Task> saveTasks(String ownerId, List<SaveTasksDTO> tasksDTO) {
        taskRepository.saveAll(tasksDTO.stream().map(dto -> dto.toObject(ownerId)).toList());

        return this.getAll(ownerId);
    }
}
