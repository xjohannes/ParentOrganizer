package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.controller.exception.TaskNotFoundException;
import com.axeweb.parentorganizr.model.Task;
import com.axeweb.parentorganizr.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Integer id) {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Task create(@RequestBody @Valid Task task) {
        return taskRepository.save(task);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Task update(@PathVariable Integer id, @RequestBody @Valid Task task) {
        Task existingTask = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        existingTask.setTaskName(task.getTaskName());
        existingTask.setLocation(task.getLocation());
        if (task.getLeader() != null) {
            existingTask.setLeader(task.getLeader());
        }
        if (task.getDescription() != null) {
            existingTask.setDescription(task.getDescription());
        }
        return taskRepository.save(existingTask);
    }


    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        taskRepository.deleteById(id);
    }
}
