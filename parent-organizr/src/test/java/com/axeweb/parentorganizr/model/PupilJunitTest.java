package com.axeweb.parentorganizr.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PupilJunitTest {

    @Test
    void shouldCreatePupil() {
        LocalDate birthdate = LocalDate.of(2018, 3, 12);
        Pupil pupil = new Pupil("Jane", "Austen", birthdate);
        pupil.setFirstName("Jane");
        pupil.setLastName("Austen");

        assertEquals("Jane", pupil.getFirstName());

    }
}
