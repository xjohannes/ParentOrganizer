package com.axeweb.parentorganizr.repository;

import com.axeweb.parentorganizr.model.Parent;
import com.axeweb.parentorganizr.model.Pupil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJdbcTest
public class JdbcTests {
    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Test
    public void testFindAll() {
        LocalDate birthdate = LocalDate.of(2018, 3, 12);
        Pupil pupil = new Pupil("John", "Doe", birthdate);

        Parent parent = new Parent("Axel", "Webster", "12345678");

        parentRepository.save(parent);
        pupilRepository.save(pupil);

        parent.addChild(pupil);

        assertThat(pupilRepository.findAll().size() == 6).isSameAs(true);

        Pupil pupilFromRepository = pupilRepository.findAll().getLast();

        assertThat(pupilFromRepository.getFirstName()).isEqualTo("John");
    }
}
