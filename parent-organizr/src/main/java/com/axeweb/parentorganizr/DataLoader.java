package com.axeweb.parentorganizr;

import com.axeweb.parentorganizr.model.Location;
import com.axeweb.parentorganizr.model.Parent;
import com.axeweb.parentorganizr.model.Task;
import com.axeweb.parentorganizr.model.Watch;
import com.axeweb.parentorganizr.repository.LocationRepository;
import com.axeweb.parentorganizr.repository.ParentRepository;
import com.axeweb.parentorganizr.repository.TaskRepository;
import com.axeweb.parentorganizr.repository.WatchRepository;
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
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private final ObjectMapper objectMapper;
    private final LocationRepository locationRepository;
    private final ParentRepository parentRepository;
    private final TaskRepository taskRepository;
    private final WatchRepository watchRepository;
    private static final String logText = "Loading data into database from json {} ";
    private static final String errorText = "Failed to load data from json file: \n {}";

    public DataLoader(ObjectMapper objectMapper, LocationRepository locationRepository, ParentRepository parentRepository, TaskRepository taskRepository, WatchRepository watchRepository) {
        this.objectMapper = objectMapper;
        this.locationRepository = locationRepository;
        this.parentRepository = parentRepository;
        this.taskRepository = taskRepository;
        this.watchRepository = watchRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadParents();
        loadLocations();
        loadTasks();
        loadWatches();
    }
    private void loadParents() {
        long count = parentRepository.count();
        log.info("Parent count in database: {}", count);
        if(parentRepository.count() == 0) {
            String PARENTS_JSON = "/data/parents.json";
            log.info(logText,  PARENTS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(PARENTS_JSON)) {
                Parent[] parents = objectMapper.readValue(inputStream, Parent[].class);
                parentRepository.saveAll(Arrays.asList(parents));
            } catch (IOException e) {
                throw new RuntimeException(errorText, e);
            }
        }
    }

    private void loadLocations() {
        long count = locationRepository.count();
        log.info("Location count in database: {}", count);
        if(locationRepository.count() == 0) {
            String LOCATIONS_JSON = "/data/locationData.json";
            log.info(logText,  LOCATIONS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(LOCATIONS_JSON)) {
               Location[] locations = objectMapper.readValue(inputStream, Location[].class);
                locationRepository.saveAll(Arrays.asList(locations));
            } catch (IOException e) {
                throw new RuntimeException(errorText, e);
            }
        }
    }

    private void loadTasks() {
        long count = taskRepository.count();
        log.info("Task count in database: {}", count);
        if(taskRepository.count() == 0) {
            String TASKS_JSON = "/data/taskData.json";
            log.info(logText,  TASKS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(TASKS_JSON)) {
               Task[] tasks = objectMapper.readValue(inputStream, Task[].class);
                taskRepository.saveAll(Arrays.asList(tasks));
            } catch (IOException e) {
                throw new RuntimeException(errorText, e);
            }
        }
    }

    private void loadWatches() {
        long count = watchRepository.count();
        log.info("Watch count in database: {}", count);
        if(watchRepository.count() == 0) {
            String WATCH_JSON = "/data/watchData.json";
            log.info(logText,  WATCH_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(WATCH_JSON)) {
               Watch[] watches = objectMapper.readValue(inputStream, Watch[].class);
                watchRepository.saveAll(Arrays.asList(watches));
            } catch (IOException e) {
                throw new RuntimeException(errorText, e);
            }
        }
    }
}
