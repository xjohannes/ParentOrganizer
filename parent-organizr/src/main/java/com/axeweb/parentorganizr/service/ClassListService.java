package com.axeweb.parentorganizr.service;

import com.axeweb.parentorganizr.model.ClassList;
import com.axeweb.parentorganizr.model.Parent;
import com.axeweb.parentorganizr.model.Pupil;
import com.axeweb.parentorganizr.repository.ClassListRepository;
import com.axeweb.parentorganizr.repository.ParentRepository;
import com.axeweb.parentorganizr.repository.PupilRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Component
public class ClassListService {
    private final ClassListRepository classRepository;
    private final ParentRepository parentRepository;
    private final PupilRepository pupilRepository;
    private String classLetter = "A";
    private String startYear;

    public ClassListService(ClassListRepository classRepository, ParentRepository parentRepository, PupilRepository pupilRepository) {
        this.classRepository = classRepository;
        this.parentRepository = parentRepository;
        this.pupilRepository = pupilRepository;
    }

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
        String[] words = line.split("\\|");
        // Removes header line
        if (words.length == 3) {
            this.classLetter = words[0];
            int currentMonth = LocalDate.now().getMonthValue();
            int currentYear = LocalDate.now().getYear();
            if (currentMonth > 8) {
                currentYear += 1;
            }
            int classNumber = currentYear - Integer.parseInt(words[2]);
            startYear = words[2];
            String className = classLetter + classNumber;
            ClassList classList = new ClassList(classLetter, className, startYear);


            classRepository.save(classList);
            return;
        }
        // Remove lines that does not contain a number as the 3 element
        if (!words[2].matches(".*\\d.*")) {
            return;
        }
        classRepository.findById(String.valueOf(1)).ifPresent(classList -> {
            Pupil pupil = new Pupil(words[0], words[1], LocalDate.parse(words[2]));
            pupil.setClassId(classList.getClassLetter());
            pupilRepository.save(pupil);
            classList.addPupil(pupil);
            classRepository.save(classList);
        });
        Pupil pupil = new Pupil(words[0], words[2], LocalDate.parse(words[2]));
        pupil.setFirstName(words[0]);
        //pupil.setLastName(words[2]);
        Parent parent1 = new Parent(words[1], words[2], "922 54 876");
        parent1.setFirstName(words[1]);
        parent1.setLastName(words[2]);
        parent1.addChild(pupil);

        parentRepository.save(parent1);
        pupilRepository.save(pupil);
        if (words.length == 5) {
            Parent parent2 = new Parent(words[3], words[4], "922 54 876");
            parent2.setFirstName(words[3]);
            parent2.setLastName(words[4]);
            parent2.addChild(pupil);
            parentRepository.save(parent2);
        }

    }
}
