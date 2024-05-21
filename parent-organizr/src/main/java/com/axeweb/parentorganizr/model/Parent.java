package com.axeweb.parentorganizr.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Data
public class Parent {

    @Id
    private Integer id;

    private String firstName;
    private String lastName;
    protected Set<PupilRef> children = new HashSet<>();
    protected String email;
    protected String phoneNumber;

    public Parent(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    // Setters
    public void addChild(Pupil pupil) {
        this.children.add(createPupilRef(pupil));
    }

    private PupilRef createPupilRef(Pupil pupil) {
        Assert.notNull(pupil, "Pupil must not be null");
        Assert.notNull(pupil.getId(), "Pupil id must not be null");
        return new PupilRef(pupil.getId());
    }
}
