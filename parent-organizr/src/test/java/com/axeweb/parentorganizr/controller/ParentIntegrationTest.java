package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.model.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
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
        assertThat(parents.length).isEqualTo(30);
    }
//
//    @Test
//    void shouldFindParentById() {
//        ResponseEntity<Parent> response = restTemplate.exchange("/parents/1", HttpMethod.GET, null, Parent.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
//    }
//
//    @Test
//    void shouldThrowNotFoundWhenInvalidID() {
//        ResponseEntity<Parent> response = restTemplate.exchange("/parents/1000", HttpMethod.GET, null, Parent.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }

    @Test
    @Rollback
    void shouldCreateParent() {
        Parent parent = new Parent("Mary", "Janeson", "98712346");
        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");
        restTemplate.delete("/parents/" + response.getBody().getId());

    }

    // Test that the previous method actually rolled back
    @Test
    void shouldRollbackAfterCreateParent() {
        Parent[] parents = restTemplate.getForObject("/parents", Parent[].class);
        assertThat(parents.length).isEqualTo(30);
    }

//    @Test
//    void shouldNotCreateParentWhenValidationFails() {
//        Parent parent = new Parent();
//        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//    }
//
//    @Test
//    @Rollback
//    void shouldUpdateParent() {
//        Parent parent = new Parent("Mary", "Janeson", "98712345");
//        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getId()).isNotNull();
//        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");
//
//        parent.setFirstName("Mary Jane");
//        restTemplate.put("/parents/" + response.getBody().getId(), parent);
//        ResponseEntity<Parent> updatedResponse = restTemplate.getForEntity("/parents/" + response.getBody().getId(), Parent.class);
//        assertThat(updatedResponse.getBody().getFirstName()).isEqualTo("Mary Jane");
//    }
//
//    @Test
//    void shouldNotUpdateParentWhenNotFound() {
//        Parent parent = new Parent("Mary", "Janeson", "98712345");
//        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getId()).isNotNull();
//        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");
//
//        parent.setFirstName("Mary Jane");
//        restTemplate.put("/parents/1000", parent);
//        ResponseEntity<Parent> updatedResponse = restTemplate.getForEntity("/parents/" + response.getBody().getId(), Parent.class);
//        assertThat(updatedResponse.getBody().getFirstName()).isEqualTo("Mary");
//    }
//
//    @Test
//    @Rollback
//    void shouldDeleteParent() {
//        Parent parent = new Parent("Mary", "Janeson", "98712345");
//        ResponseEntity<Parent> response = restTemplate.postForEntity("/parents", parent, Parent.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getId()).isNotNull();
//        assertThat(response.getBody().getFirstName()).isEqualTo("Mary");
//
//        restTemplate.delete("/parents/" + response.getBody().getId());
//        ResponseEntity<Parent> updatedResponse = restTemplate.getForEntity("/parents/" + response.getBody().getId(), Parent.class);
//        assertThat(updatedResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
}
