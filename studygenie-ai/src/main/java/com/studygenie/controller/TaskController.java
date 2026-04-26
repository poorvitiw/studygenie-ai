package com.studygenie.controller;

import com.studygenie.model.Priority;
import com.studygenie.model.Status;
import com.studygenie.service.TaskService;
import org.springframework.web.bind.annotation.*;
import com.studygenie.model.Task;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority
    ) {
        if (status != null) {
            return taskService.getTasksByStatus(status);
        } else if (priority != null) {
            return taskService.getTasksByPriority(priority);
        } else {
            return taskService.getAllTasks();
        }
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
    @PutMapping("/{id}/complete")
    public Task markAsCompleted(@PathVariable Long id) {

        Task task = taskService.getTaskById(id);

        task.setStatus(Status.DONE);

        return taskService.createTask(task);
    }
}