package com.axeweb.parentorganizr.model;

import java.time.LocalDate;
import java.util.Set;

public class Pupil extends Person {

    private LocalDate birthdate;
    private Set<Parent> parents;

    public Pupil(String firstName, String lastName, Set<Parent> parents) {
        super(firstName, lastName);
        this.parents = parents;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
    public Set<Parent> getParents() {
        return parents;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pupil pupil = (Pupil) o;

        return super.getId() == pupil.getId() && firstName.equals(pupil.firstName) && lastName.equals(pupil.lastName) && birthdate.equals(pupil.birthdate) && parents.equals(pupil.parents);
    }
    @Override
    public int hashCode() {
        int result = super.getId();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthdate.hashCode();
        result = 31 * result + parents.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return "Pupil{" +
                "id='" + super.getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", parents=" + parents +
                '}';
    }
}
