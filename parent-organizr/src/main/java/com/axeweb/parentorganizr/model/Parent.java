package com.axeweb.parentorganizr.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Data
public class Parent {
    @Id
    private Integer id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    protected Set<PupilRef> children = new HashSet<>();
    protected String email;
    @NotEmpty
    protected String phoneNumber;
    @Version
    protected Integer version;

    // Constructors
    public Parent() {
    }

    public Parent(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public Parent(int id,String firstName, String lastName, String phoneNumber) {

        this.id = id;

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
