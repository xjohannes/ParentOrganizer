package com.axeweb.parentorganizr.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Data
public class Task {
    @Id
    private Integer id;
    @NotEmpty
    private String taskName;
    private Integer leader;
    @NotEmpty
    private Integer location;
    private String description;
    @Version
    private int version;

    public Task() {
    }

    public Task(String taskName, int leader, int location, String description) {
        this.taskName = taskName;
        this.leader = leader;
        this.location = location;
        this.description = description;
    }
}
