package com.axeweb.parentorganizr.model;

import java.util.Set;

public class Parent extends Person {
    private Set<Pupil> children;

    public Parent(String firstName, String phoneNumber) {
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }


    public Set<Pupil> getChildren() {
        return children;
    }
    public void setChildren(Set<Pupil> children) {
        this.children = children;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return firstName.equals(parent.firstName) && lastName.equals(parent.lastName) && email.equals(parent.email) && phoneNumber.equals(parent.phoneNumber) && address.equals(parent.address) && children.equals(parent.children);
    }
    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + children.hashCode();
        return result;
    }
    @Override
    public String toString() {
        String childrenString = this.children != null ? children.toString(): "";
        return "Parent{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", children=" + childrenString +
                '}';
    }

}
