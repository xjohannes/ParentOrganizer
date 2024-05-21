package com.axeweb.parentorganizr.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class Pupil {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String classId;

    public Pupil(String firstName, String lastName, LocalDate birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }
}
