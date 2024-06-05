package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.model.Location;
import com.axeweb.parentorganizr.model.Parent;
import com.axeweb.parentorganizr.model.Task;
import com.axeweb.parentorganizr.model.Watch;
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

import java.sql.Date;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WatchIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldFindAllWatches() {
        // /api/watches
        Watch[] watches = restTemplate.getForObject("/watches", Watch[].class);
        assertThat(watches).hasSize(230);
    }

    @Test
    void shouldFindWatchById() {
        Watch watch = restTemplate.getForObject("/watches/1", Watch.class);
        assertThat(watch).isNotNull();
        assertThat(watch.getId()).isEqualTo(1);
    }

    @Test
    void shouldThrowNotFoundWhenInvalidID() {
        ResponseEntity<Watch> response = restTemplate.getForEntity("/watches/1000", Watch.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateWatch() {
        Parent parent = new Parent("Mary", "Janeson", "98712378");
        ResponseEntity<Parent> responseParent = restTemplate.postForEntity("/parents", parent, Parent.class);
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> responseLocation = restTemplate.postForEntity("/locations", location, Location.class);

        int parentId = Objects.requireNonNull(responseParent.getBody()).getId();
        int locationId = Objects.requireNonNull(responseLocation.getBody()).getId();

        Task task = new Task("Trafikkvakt",parentId, locationId,  "Være trafikkvakt på skoleveien");
        ResponseEntity<Task> responseTask = restTemplate.postForEntity("/tasks", task, Task.class);
        int taskId = Objects.requireNonNull(responseTask.getBody()).getId();
        Date date = java.sql.Date.valueOf("2024-10-01");
        Watch watch = new Watch( taskId,parentId, date, "08:15");
        ResponseEntity<Watch> response = restTemplate.postForEntity("/watches", watch, Watch.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getStartTime()).isEqualTo("08:15");

        // Clean up
        restTemplate.delete(STR."/watches/\{response.getBody().getId()}");
    }

    @Test
    void shouldNotCreateWatchWithInvalidData() {
        Watch watch = new Watch();
        ResponseEntity<Watch> response = restTemplate.postForEntity("/watches", watch, Watch.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldUpdateWatch() {
        Parent parent = new Parent("Mary", "Janeson", "98712379");
        ResponseEntity<Parent> responseParent = restTemplate.postForEntity("/parents", parent, Parent.class);
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> responseLocation = restTemplate.postForEntity("/locations", location, Location.class);

        int parentId = Objects.requireNonNull(responseParent.getBody()).getId();
        int locationId = Objects.requireNonNull(responseLocation.getBody()).getId();

        Task task = new Task("Trafikkvakt",parentId, locationId,  "Være trafikkvakt på skoleveien");
        ResponseEntity<Task> responseTask = restTemplate.postForEntity("/tasks", task, Task.class);
        int taskId = Objects.requireNonNull(responseTask.getBody()).getId();
        Date date = java.sql.Date.valueOf("2024-10-01");

        Watch watch = new Watch( taskId,parentId, date, "08:15");
        ResponseEntity<Watch> response = restTemplate.postForEntity("/watches", watch, Watch.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(response.getBody()).getStartTime()).isEqualTo("08:15");

        Watch updatedWatch = new Watch( taskId, parentId, date, "08:30");
        HttpEntity<Watch> requestUpdate = new HttpEntity<>(updatedWatch);
        ResponseEntity<Watch> responseUpdate = restTemplate.exchange("/watches/" + response.getBody().getId(), HttpMethod.PUT, requestUpdate, Watch.class);
       assertThat(responseUpdate.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseUpdate.getBody()).isNotNull();
       assertThat(Objects.requireNonNull(responseUpdate.getBody()).getStartTime()).isEqualTo("08:30");

        // Clean up
        restTemplate.delete(STR."/watches/\{response.getBody().getId()}");
    }

    @Test
    void shouldNotUpdateWatchWithInvalidData() {
        Watch watch = new Watch();
        HttpEntity<Watch> requestUpdate = new HttpEntity<>(watch);
        ResponseEntity<Watch> response = restTemplate.exchange("/watches/1", HttpMethod.PUT, requestUpdate, Watch.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldDeleteWatch() {
        Parent parent = new Parent("Mary", "Janeson", "98712379");
        ResponseEntity<Parent> responseParent = restTemplate.postForEntity("/parents", parent, Parent.class);
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> responseLocation = restTemplate.postForEntity("/locations", location, Location.class);

        int parentId = Objects.requireNonNull(responseParent.getBody()).getId();
        int locationId = Objects.requireNonNull(responseLocation.getBody()).getId();

        Task task = new Task("Trafikkvakt",parentId, locationId,  "Være trafikkvakt på skoleveien");
        ResponseEntity<Task> responseTask = restTemplate.postForEntity("/tasks", task, Task.class);
        int taskId = Objects.requireNonNull(responseTask.getBody()).getId();
        Date date = java.sql.Date.valueOf("2024-10-01");

        Watch watch = new Watch( taskId,parentId, date, "08:15");
        ResponseEntity<Watch> response = restTemplate.postForEntity("/watches", watch, Watch.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(response.getBody()).getStartTime()).isEqualTo("08:15");

        restTemplate.delete("/watches/" + response.getBody().getId());
        ResponseEntity<Watch> responseDeleted = restTemplate.getForEntity("/watches/" + response.getBody().getId(), Watch.class);
        assertThat(responseDeleted.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }



}
