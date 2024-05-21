package com.axeweb.parentorganizr.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;

public class PupilHamcrestTest {
    @Test
    void shouldCreatePupil() {
        Parent parent = mock(Parent.class);
        Set<Parent> parents = new HashSet<>();
        parents.add(parent);
        LocalDate birthdate = LocalDate.of(2018, 3, 12);
        Pupil pupil = new Pupil("Jane", "Austen", birthdate);



    }
}
