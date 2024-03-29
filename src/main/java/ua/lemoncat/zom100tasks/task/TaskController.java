package ua.lemoncat.zom100tasks.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ua.lemoncat.zom100tasks.dto.CreateTaskDTO;
import ua.lemoncat.zom100tasks.dto.SaveTasksDTO;
import ua.lemoncat.zom100tasks.dto.TaskDTO;
import ua.lemoncat.zom100tasks.dto.UpdateTaskDTO;

import java.util.List;
import java.util.UUID;

@CrossOrigin(maxAge = 3600L)
@RestController
@RequestMapping("zom100/api/v1/tasks")
@Slf4j
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDTO> getAllTasks(@AuthenticationPrincipal Jwt principal) {
        return taskService.getAll(principal.getSubject());
    }

    @PostMapping
    public List<TaskDTO> addTask(@AuthenticationPrincipal Jwt principal, @RequestBody CreateTaskDTO createTaskDTO) {
        return taskService.create(createTaskDTO, principal.getSubject());
    }

    @PutMapping
    public List<TaskDTO> updateTask(@AuthenticationPrincipal Jwt principal, @RequestBody UpdateTaskDTO updateTaskDTO) {
        return taskService.update(updateTaskDTO, principal.getSubject());
    }

    @DeleteMapping("{taskId}")
    public List<TaskDTO> deleteTask(@AuthenticationPrincipal Jwt principal, @PathVariable UUID taskId) {
        return taskService.delete(taskId, principal.getSubject());
    }

    @PatchMapping("{taskId}/complete")
    public List<TaskDTO> completeTask(@AuthenticationPrincipal Jwt principal, @PathVariable UUID taskId) {
        return taskService.changeStatus(taskId, principal.getSubject(), Status.COMPLETED);
    }

    @PatchMapping("{taskId}/in-progress")
    public List<TaskDTO> inProgressTask(@AuthenticationPrincipal Jwt principal, @PathVariable UUID taskId) {
        return taskService.changeStatus(taskId, principal.getSubject(), Status.IN_PROGRESS);
    }

    @PostMapping("save")
    public List<TaskDTO> saveTasks(@AuthenticationPrincipal Jwt principal, @RequestBody List<SaveTasksDTO> tasks) {
        return taskService.saveTasks(principal.getSubject(), tasks);
    }
}
