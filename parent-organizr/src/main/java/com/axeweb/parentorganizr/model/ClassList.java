package com.axeweb.parentorganizr.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
public class ClassList {
    @Id
    private Integer id;
    private String classLetter;
    private String className;
    private String startYear;
    private Set<Pupil> pupils = new HashSet<>();

    public ClassList(String classLetter, String className, String startYear) {
        this.classLetter = classLetter;
        this.className = className;
        this.startYear = startYear;
    }

    public ClassList addPupil(Pupil pupil) {
        this.pupils.add(pupil);
        return this;
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
        return classLetter.equals(classList.classLetter) && className.equals(classList.className) && startYear.equals(classList.startYear);
    }

    @Override
    public int hashCode() {
        int result = classLetter.hashCode();
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
                ", classLetter='" + classLetter + '\'' +
                ", className='" + className + '\'' +
                ", startYear='" + startYear + '\'' +
                ", pupils=" + pupils +
                '}';
    }
}
