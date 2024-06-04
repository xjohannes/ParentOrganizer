package com.axeweb.parentorganizr;

import com.axeweb.parentorganizr.model.Task;
import com.axeweb.parentorganizr.repository.TaskRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Component
public class TaskDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TaskDataLoader.class);
    private final ObjectMapper objectMapper;
    private final TaskRepository taskRepository;

    public TaskDataLoader(ObjectMapper objectMapper, TaskRepository taskRepository) {
        this.objectMapper = objectMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = taskRepository.count();
        log.info("Task count in database: {}", count);
        if(taskRepository.count() == 9) {
            String TASKS_JSON = "/data/taskData.json";
            log.info("Loading data into database from json {} ",  TASKS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(TASKS_JSON)) {
               Task[] tasks = objectMapper.readValue(inputStream, Task[].class);
               System.out.println("TASKS" + Arrays.asList(tasks));
                taskRepository.saveAll(Arrays.asList(tasks));
                taskRepository.findAll().forEach(task -> log.info("Task loaded: {}", task));
            } catch (IOException e) {
                throw new RuntimeException("Failed to load data from json file: \n {}", e);
            }
        }
    }

}
