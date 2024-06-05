package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class LocationIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldFindAllLocations() {
        // /api/locations
        Location[] locations = restTemplate.getForObject("/locations", Location[].class);
        assertThat(locations).hasSize(30);
    }

    @Test
    void shouldFindLocationById() {
        ResponseEntity<Location> response = restTemplate.getForEntity("/locations/1", Location.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1);
    }

    @Test
    void shouldThrowNotFoundWhenInvalidID() {
        ResponseEntity<Location> response = restTemplate.getForEntity("/locations/1000", Location.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateLocation() {
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> response = restTemplate.postForEntity("/locations", location, Location.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();

        // Clean up
        restTemplate.delete("/locations/" + response.getBody().getId());
    }

    @Test
    void shouldNotCreateLocationWithInvalidData() {
        Location location = new Location();
        ResponseEntity<Location> response = restTemplate.postForEntity("/locations", location, Location.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldUpdateLocation() {
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> response = restTemplate.postForEntity("/locations", location, Location.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();

        // Update location
        Location updatedLocation = new Location("Nedre fotgjengerfelt", "Krysset", "Ute" );
        restTemplate.put("/locations/" + response.getBody().getId(), updatedLocation);

        // Verify location is updated
        ResponseEntity<Location> updatedResponse = restTemplate.getForEntity("/locations/" + response.getBody().getId(), Location.class);
        assertThat(Objects.requireNonNull(updatedResponse.getBody()).getId()).isEqualTo(response.getBody().getId());
        assertThat(updatedResponse.getBody().getLocationName()).isEqualTo("Nedre fotgjengerfelt");

        // Clean up
        restTemplate.delete("/locations/" + response.getBody().getId());
    }

    @Test
    void shouldNotUpdateLocationWhenNotFound() {
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> response = restTemplate.postForEntity("/locations", location, Location.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();

        // Update location
        Location updatedLocation = new Location("Nedre fotgjengerfelt", "Krysset", "Ute" );
        restTemplate.put("/locations/1000", updatedLocation);

        // Verify location is not updated
        ResponseEntity<Location> updatedResponse = restTemplate.getForEntity("/locations/" + response.getBody().getId(), Location.class);
        assertThat(updatedResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updatedResponse.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(updatedResponse.getBody()).getId()).isEqualTo(response.getBody().getId());
        assertThat(updatedResponse.getBody().getLocationName()).isEqualTo("Øvre fotgjengerfelt");

        // Clean up
        restTemplate.delete("/locations/" + response.getBody().getId());
    }

    @Test
    void shouldDeleteLocation() {
        Location location = new Location("Øvre fotgjengerfelt", "Krysset", "Ute" );
        ResponseEntity<Location> response = restTemplate.postForEntity("/locations", location, Location.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();

        // Delete location
         restTemplate.delete("/locations/" + response.getBody().getId());

        // Verify location is deleted
        ResponseEntity<Location> deletedResponse = restTemplate.getForEntity("/locations/" + response.getBody().getId(), Location.class);
        assertThat(deletedResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
