package com.axeweb.parentorganizr.service;

import com.axeweb.parentorganizr.model.Parent;
import com.axeweb.parentorganizr.model.Pupil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
public class ClassListService {

    public void parseClassList(MultipartFile classList) throws IOException {
        // parse the multipart file into a list of Pupils
        InputStream inputStream = classList.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .forEach(this::handleLine);
    }

    private void handleLine(String line) {
        // parse the line into a Pupil object
        if (line.isBlank()) {
            return;
        }
        // Removes header line
        if (line.split("\\|").length <= 3) {
            return;
        }
        // Remove lines that does not contain a number in as the 3 element
        if (!line.split("\\|")[2].matches(".*\\d.*")) {
            return;
        }
        Parent parent1 = new Parent(line.split("\\|")[1], line.split("\\|")[2]);
        Set<Parent> parents = new HashSet<>(Set.of(parent1));
        if (line.split("\\|").length == 5) {
            Parent parent2 = new Parent(line.split("\\|")[3], line.split("\\|")[4]);
            parents.add(parent2);
        }
        new Pupil(line.split("\\|")[0], line.split("\\|")[1], parents);
    }
}
