package com.studygenie.service;

import org.springframework.stereotype.Service;

import com.studygenie.model.Task;
import com.studygenie.model.Priority;
import com.studygenie.model.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AIService {

    public String askAI(String prompt) {
        return "StudyGenie AI says:\n\n" +
                "You asked: " + prompt + "\n\n" +
                "Here’s a simple explanation:\n" +
                "Deadlock happens when multiple processes are stuck waiting for each other, " +
                "so none of them can proceed.";
    }

    public String generateStudyPlan(String topic, int days) {

        StringBuilder plan = new StringBuilder();
        plan.append(" Smart Study Plan for ").append(topic).append("\n\n");

        String[] topics;

        if (topic.equalsIgnoreCase("Operating System")) {
            topics = new String[]{
                    "Introduction to OS",
                    "Process Scheduling",
                    "Deadlocks",
                    "Memory Management",
                    "File Systems"
            };
        } else if (topic.equalsIgnoreCase("DBMS")) {
            topics = new String[]{
                    "Introduction to DBMS",
                    "ER Model",
                    "Normalization",
                    "SQL Queries",
                    "Transactions"
            };
        } else {
            topics = new String[]{
                    "Basics",
                    "Core Concepts",
                    "Advanced Concepts",
                    "Practice",
                    "Revision"
            };
        }

        for (int i = 0; i < days; i++) {
            String content = topics[i % topics.length];
            plan.append("Day ").append(i + 1).append(": ").append(content).append("\n");
        }

        return plan.toString();
    }


    public List<Task> generateTasksFromPlan(String topic, int days) {

        List<Task> tasks = new ArrayList<>();

        String[] topics = {
                "Basics",
                "Core Concepts",
                "Advanced Topics",
                "Practice",
                "Revision"
        };

        for (int i = 0; i < days; i++) {

            Task task = new Task();

            task.setTitle(topic + " - Day " + (i + 1));
            task.setDescription(topics[i % topics.length]);

            task.setDeadline(LocalDate.now().plusDays(i));

            task.setPriority(Priority.MEDIUM);
            task.setStatus(Status.TODO);

            tasks.add(task);
        }

        return tasks;
    }
}
