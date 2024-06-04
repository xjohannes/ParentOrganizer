package com.axeweb.parentorganizr.model;

import org.springframework.data.annotation.Id;

public class Task {
    @Id
    private Integer id;
    private final String taskName;
    private final int leader;
    private final int location;
    private final int description;
    private int version;

    public Task(String taskName, int leader, int location, int description) {
        this.taskName = taskName;
        this.leader = leader;
        this.location = location;
        this.description = description;
    }
}
