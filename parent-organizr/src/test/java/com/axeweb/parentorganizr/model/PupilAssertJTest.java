package com.axeweb.parentorganizr.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PupilAssertJTest {

    @Test
    void shouldCreatePupil() {
        LocalDate birthdate = LocalDate.of(2018, 3, 12);
        Pupil pupil = new Pupil("Jane", "Austen", birthdate);


        assertThat(pupil.getFirstName())
                .as("First name should be Jane")
                .startsWith("J")
                .endsWith("e")
                .contains("an")
                .isEqualTo("Jane");

    }

}
