package com.axeweb.parentorganizr.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("parent_pupil")
public class PupilRef {
    private final int pupil;

    public PupilRef(Integer pupil) {
        this.pupil = pupil;
    }
}

