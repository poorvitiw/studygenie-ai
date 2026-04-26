package com.studygenie.repository;

import com.studygenie.model.Priority;
import com.studygenie.model.Status;
import com.studygenie.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);
}
