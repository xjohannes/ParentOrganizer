package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.model.Location;
import com.axeweb.parentorganizr.model.Parent;
import com.axeweb.parentorganizr.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class TaskIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldFindAllTasks() {
        // /api/tasks
        Task[] tasks = restTemplate.getForObject("/tasks", Task[].class);
        assertThat(tasks).hasSize(80);
    }

    @Test
    void shouldFindTaskById() {
        Task task = restTemplate.getForObject("/tasks/1", Task.class);
        assertThat(task).isNotNull();
        assertThat(task.getId()).isEqualTo(1);
    }

    @Test
    void shouldThrowNotFoundWhenInvalidID() {
        ResponseEntity<Location> response = restTemplate.getForEntity("/locations/1000", Location.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateTask() {
        Parent parent = new Parent("Mary", "Janeson", "98712378");
        ResponseEntity<Parent> responseParent = restTemplate.postForEntity("/parents", parent, Parent.class);
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> responseLocation = restTemplate.postForEntity("/locations", location, Location.class);
        int parentId = Objects.requireNonNull(responseParent.getBody()).getId();
        int locationId = Objects.requireNonNull(responseLocation.getBody()).getId();

        Task task = new Task("Basar",  parentId, locationId);
        ResponseEntity<Task> response = restTemplate.postForEntity("/tasks", task, Task.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getTaskName()).isEqualTo("Basar");

        // Clean up
        restTemplate.delete(STR."/tasks/\{response.getBody().getId()}");
    }

    @Test
    void shouldNotCreateTaskWithInvalidData() {
        Task task = new Task();
        ResponseEntity<Task> response = restTemplate.postForEntity("/tasks", task, Task.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldUpdateTask() {
        Parent parent = new Parent("Mary", "Janeson", "98712379");
        ResponseEntity<Parent> responseParent = restTemplate.postForEntity("/parents", parent, Parent.class);
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> responseLocation = restTemplate.postForEntity("/locations", location, Location.class);
        int parentId = Objects.requireNonNull(responseParent.getBody()).getId();
        int locationId = Objects.requireNonNull(responseLocation.getBody()).getId();

        Task task = new Task("Basar",  parentId, locationId);
        ResponseEntity<Task> response = restTemplate.postForEntity("/tasks", task, Task.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getTaskName()).isEqualTo("Basar");

        // Update the task
        task.setTaskName("Cafe");
        HttpEntity<Task> requestUpdate = new HttpEntity<>(task);
        ResponseEntity<Task> putResponse = restTemplate.exchange("/tasks/" + response.getBody().getId(), HttpMethod.PUT, requestUpdate, Task.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        ResponseEntity<Task> updatedResponse = restTemplate.getForEntity("/tasks/" + response.getBody().getId(), Task.class);
        assertThat(updatedResponse.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(updatedResponse.getBody()).getTaskName()).isEqualTo("Cafe");

        // Clean up
        restTemplate.delete("/tasks/" + response.getBody().getId());
    }

    @Test
    void shouldNotUpdateTaskWhenNotFound() {
        Parent parent = new Parent("Mary", "Janeson", "98712380");
        ResponseEntity<Parent> responseParent = restTemplate.postForEntity("/parents", parent, Parent.class);
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> responseLocation = restTemplate.postForEntity("/locations", location, Location.class);
        int parentId = Objects.requireNonNull(responseParent.getBody()).getId();
        int locationId = Objects.requireNonNull(responseLocation.getBody()).getId();

        Task task = new Task("Basar",  parentId, locationId);
        ResponseEntity<Task> response = restTemplate.postForEntity("/tasks", task, Task.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getTaskName()).isEqualTo("Basar");

        // Update the task
        task.setTaskName("Cafe");
        HttpEntity<Task> requestUpdate = new HttpEntity<>(task);
        ResponseEntity<Task> putResponse = restTemplate.exchange("/tasks/1000", HttpMethod.PUT, requestUpdate, Task.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        ResponseEntity<Task> updatedResponse = restTemplate.getForEntity("/tasks/" + response.getBody().getId(), Task.class);
        assertThat(updatedResponse.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(updatedResponse.getBody()).getTaskName()).isEqualTo("Basar");

        // Clean up
        restTemplate.delete("/tasks/" + response.getBody().getId());
    }

    @Test
    void shouldDeleteTask() throws InterruptedException {
        Parent parent = new Parent("Mary", "Janeson", "98712381");
        ResponseEntity<Parent> responseParent = restTemplate.postForEntity("/parents", parent, Parent.class);
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> responseLocation = restTemplate.postForEntity("/locations", location, Location.class);

        int parentId = Objects.requireNonNull(responseParent.getBody()).getId();
        int locationId = Objects.requireNonNull(responseLocation.getBody()).getId();

        Task task = new Task("Basar",  parentId, locationId);
        ResponseEntity<Task> response = restTemplate.postForEntity("/tasks", task, Task.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getTaskName()).isEqualTo("Basar");
        int taskId = Objects.requireNonNull(response.getBody()).getId();

        // Delete task
        restTemplate.delete("/tasks/" + taskId);
        // Verify task is deleted
        ResponseEntity<Task> deletedResponse = restTemplate.getForEntity("/tasks/" + taskId, Task.class);
        assertThat(deletedResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
