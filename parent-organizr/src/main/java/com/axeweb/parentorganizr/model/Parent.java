package com.axeweb.parentorganizr.model;

import java.util.Set;

public class Parent extends Person {

    protected Set<Pupil> children;
    protected String email;
    protected String phoneNumber;
    protected String address;

    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Set<Pupil> getChildren() {
        return children;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setChildren(Set<Pupil> children) {
        this.children = children;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return super.getId() == parent.getId() && firstName.equals(parent.firstName) && lastName.equals(parent.lastName) && email.equals(parent.email) && phoneNumber.equals(parent.phoneNumber) && address.equals(parent.address) && children.equals(parent.children);
    }

    @Override
    public int hashCode() {
        int result = super.getId();
        result = result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + children.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String childrenString = this.children != null ? children.toString() : "";
        return "Parent{" +
                "id=" + super.getId() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", children=" + childrenString +
                '}';
    }

}
