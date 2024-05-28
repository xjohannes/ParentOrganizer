package com.axeweb.parentorganizr.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public abstract class Person {
    // Setters
    // Getters
    @Id
    protected int id;
    protected String firstName;
    protected String lastName;

    protected Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    public int hashCode() {
        int result = id;
        result = result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();

        return result;
    }
    public String toString() {
        return "Person{" +
                "id=" + id +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
