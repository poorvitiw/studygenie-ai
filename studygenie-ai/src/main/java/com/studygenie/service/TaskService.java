package com.studygenie.service;

import com.studygenie.model.Priority;
import com.studygenie.model.Status;
import com.studygenie.model.Task;
import com.studygenie.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor Injection
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Create Task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Get All Tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Update Task
    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDeadline(updatedTask.getDeadline());
        task.setPriority(updatedTask.getPriority());
        task.setStatus(updatedTask.getStatus());

        return taskRepository.save(task);
    }

    // Delete Task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Filter by Status
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    // Filter by Priority
    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    // Get Task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }
}