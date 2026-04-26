package com.studygenie.controller;

import com.studygenie.service.AIService;
import com.studygenie.service.TaskService;
import com.studygenie.model.Task;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final AIService aiService;
    private final TaskService taskService;

    public AIController(AIService aiService, TaskService taskService) {
        this.aiService = aiService;
        this.taskService = taskService;
    }

    @PostMapping("/plan")
    public String generatePlan(@RequestBody Map<String, String> request) {

        String topic = request.get("topic");
        int days = Integer.parseInt(request.get("days"));

        return aiService.generateStudyPlan(topic, days);
    }

    @PostMapping("/plan/save")
    public List<Task> generateAndSavePlan(@RequestBody Map<String, String> request) {

        String topic = request.get("topic");
        int days = Integer.parseInt(request.get("days"));

        List<Task> tasks = aiService.generateTasksFromPlan(topic, days);

        for (Task task : tasks) {
            taskService.createTask(task);
        }

        return tasks;
    }
}