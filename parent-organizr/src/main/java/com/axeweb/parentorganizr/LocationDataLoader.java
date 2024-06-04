package com.axeweb.parentorganizr;

import com.axeweb.parentorganizr.model.Location;
import com.axeweb.parentorganizr.repository.LocationRepository;
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
public class LocationDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LocationDataLoader.class);
    private final ObjectMapper objectMapper;
    private final LocationRepository locationRepository;

    public LocationDataLoader(ObjectMapper objectMapper, LocationRepository locationRepository) {
        this.objectMapper = objectMapper;
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = locationRepository.count();
        log.info("Location count in database: {}", count);
        if(locationRepository.count() == 0) {
            String LOCATIONS_JSON = "/data/locationData.json";
            log.info("Loading data into database from json {} ",  LOCATIONS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(LOCATIONS_JSON)) {
               Location[] locations = objectMapper.readValue(inputStream, Location[].class);
                locationRepository.saveAll(Arrays.asList(locations));
                locationRepository.findAll().forEach(location -> log.info("Location loaded: {}", location));
            } catch (IOException e) {
                throw new RuntimeException("Failed to load data from json file: \n {}", e);
            }
        }
    }

}
