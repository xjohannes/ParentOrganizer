package com.axeweb.parentorganizr.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassList(@NotBlank String classNumber, @NotBlank String classLetter, @NotNull String startYear, @NotBlank String filename) {

}
