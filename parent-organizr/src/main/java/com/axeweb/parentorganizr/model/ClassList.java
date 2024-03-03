package com.axeweb.parentorganizr.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassList(@NotBlank String classNumber, @NotBlank String classLetter, @Id @NotBlank String className, @NotNull String startYear, @NotBlank String filename) {

}
