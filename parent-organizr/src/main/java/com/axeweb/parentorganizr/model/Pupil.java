package com.axeweb.parentorganizr.model;

import java.time.LocalDateTime;

public record Pupil(String firstName, String lastName, LocalDateTime birthdate, String[] parents ) {

}
