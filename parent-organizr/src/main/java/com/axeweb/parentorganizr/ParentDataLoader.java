package com.axeweb.parentorganizr;

import com.axeweb.parentorganizr.model.Parent;
import com.axeweb.parentorganizr.repository.ParentRepository;
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
public class ParentDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ParentDataLoader.class);
    private final ObjectMapper objectMapper;
    private final ParentRepository parentRepository;

    public ParentDataLoader(ObjectMapper objectMapper, ParentRepository parentRepository) {
        this.objectMapper = objectMapper;
        this.parentRepository = parentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = parentRepository.count();
        log.info("Parent count in database: {}", count);
        if(parentRepository.count() == 0) {
            String PARENTS_JSON = "/data/parents.json";
            log.info("Loading data into database from json {} ",  PARENTS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(PARENTS_JSON)) {
               Parent[] parents = objectMapper.readValue(inputStream, Parent[].class);

                parentRepository.saveAll(Arrays.asList(parents));
            } catch (IOException e) {
                throw new RuntimeException("Failed to load data from json file: \n {}", e);
            }
        }
    }

}
