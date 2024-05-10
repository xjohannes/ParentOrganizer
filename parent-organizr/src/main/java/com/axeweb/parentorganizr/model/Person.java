package com.axeweb.parentorganizr.model;

import org.springframework.data.annotation.Id;

public abstract class Person {
    @Id
    protected int id;
    protected String firstName;
    protected String lastName;

    protected Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }


    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return id == parent.id && firstName.equals(parent.firstName) && lastName.equals(parent.lastName);
    }

    public int hashCode() {
        int result = id;
        result = result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();

        return result;
    }
    public String toString() {
        return "Parent{" +
                "id=" + id +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
