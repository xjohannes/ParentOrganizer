package com.axeweb.parentorganizr.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public record ClassList(@NotBlank String classNumber, @NotBlank String classLetter, @Id @NotBlank String className,
                        @NotNull String startYear, List<Pupil> pupils) {

    public ClassList addPupil(Pupil pupil) {
        List<Pupil> newPupils = new ArrayList<>(pupils);
        newPupils.add(pupil);
        return new ClassList(classNumber, classLetter, className, startYear, newPupils);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) { return false; }
        // compare the contents of the pupils array
        ClassList classList = (ClassList) o;
        if (pupils.size() != classList.pupils.size()) { return false; }
        for (Pupil pupil : pupils) {
            if (!classList.pupils.contains(pupil)) { return false; }
        }
        return classNumber.equals(classList.classNumber) && classLetter.equals(classList.classLetter) && className.equals(classList.className) && startYear.equals(classList.startYear);
    }

    @Override
    public int hashCode() {
        int result = classNumber.hashCode();
        result = 31 * result + classLetter.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + startYear.hashCode();
        for (Pupil pupil : pupils) {
            result = 31 * result + pupil.hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        return "ClassList{" +
                "classNumber='" + classNumber + '\'' +
                ", classLetter='" + classLetter + '\'' +
                ", className='" + className + '\'' +
                ", startYear='" + startYear + '\'' +
                ", pupils=" + pupils +
                '}';
    }
}
