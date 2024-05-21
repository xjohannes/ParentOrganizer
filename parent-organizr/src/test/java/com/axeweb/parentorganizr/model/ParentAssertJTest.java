package com.axeweb.parentorganizr.model;

import com.axeweb.parentorganizr.repository.PupilRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
public class ParentAssertJTest {
    @Autowired
    PupilRepository pupilRepository;




    @Test
    void shouldCreateParent() {
        Parent parent = new Parent("Janne", "Østheim", "922 54 876");

        assertThat(parent.getFirstName())
                .as("First name should be Jane")
                .startsWith("J")
                .endsWith("e")
                .contains("an")
                .isEqualTo("Jane");

    }
//    @Test
//    void shouldCreateChildren () {
//        LocalDate birthdate = LocalDate.of(2018, 3, 12);
//        Parent parent = new Parent("Gunnar", "Hansen", "922 54 876");
//        Pupil pupil = new Pupil("Frank", "Fredriksen", birthdate);
//        pupil.setClassId("1");
//        pupilRepository.save(pupil);
//
//        assertThat(parent.getChildren().size()).isEqualTo(1);
//        // assertThat(parent.getChildren()).contains(pupil);
//    }

}
