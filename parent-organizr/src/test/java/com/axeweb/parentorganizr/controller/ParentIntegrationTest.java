package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.model.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
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
class ParentIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldFindAllParents() {
        // /api/parents
        Parent[] parents = restTemplate.getForObject("/parents", Parent[].class);
        assertThat(parents).hasSize(30);
    }

    @Test
    void shouldFindParentById() {
        ResponseEntity<Parent> response = restTemplate.exchange("/parents/1", HttpMethod.GET, null, Parent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        System.out.println("PARENT" + response.getBody());
    }

    @Test
    void shouldThrowNotFoundWhenInvalidID() {
        ResponseEntity<Parent> response = restTemplate.exchange("/parents/1000", HttpMethod.GET, null, Parent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateParent() {
        Parent parent = new Parent("Mary", "Janeson", "98712345");
        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");

        // Clean up
        restTemplate.delete("/parents/" + response.getBody().getId());

    }

    @Test
    void shouldNotCreateParentWhenValidationFails() {
        Parent parent = new Parent();
        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldUpdateParent() {
        // Insert a new parent
        Parent parent = new Parent("Mary", "Janeson", "98712345");
        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");

        // Update the parent
        parent.setFirstName("Mary Jane");
        restTemplate.put("/parents/" + response.getBody().getId(), parent);
        ResponseEntity<Parent> updatedResponse = restTemplate.getForEntity("/parents/" + response.getBody().getId(), Parent.class);
        assertThat(Objects.requireNonNull(updatedResponse.getBody()).getFirstName()).isEqualTo("Mary Jane");

        // Clean up
        restTemplate.delete("/parents/" + response.getBody().getId());

    }

    @Test
    void shouldNotUpdateParentWhenNotFound() {
        Parent parent = new Parent("Mary", "Janeson", "98712345");
        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");
        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");

        parent.setFirstName("Mary Jane");
        restTemplate.put("/parents/1000", parent);
        ResponseEntity<Parent> updatedResponse = restTemplate.getForEntity("/parents/" + response.getBody().getId(), Parent.class);
        assertThat(Objects.requireNonNull(updatedResponse.getBody()).getFirstName()).isEqualTo("Mary");

        // Clean up
        restTemplate.delete("/parents/" + response.getBody().getId());
    }

    @Test
    void shouldDeleteParent() {
        Parent parent = new Parent("Mary", "Janeson", "98712345");
        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");

        restTemplate.delete("/parents/" + response.getBody().getId());
        ResponseEntity<Parent> updatedResponse = restTemplate.getForEntity("/parents/" + response.getBody().getId(), Parent.class);
        assertThat(updatedResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
