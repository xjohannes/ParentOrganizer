package com.axeweb.parentorganizr.model;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Watch {

    @Id
    private Integer id;
    @NotNull
    private Integer task;
    @NotNull
    private Integer parent;
    @Future
    private Date watchDate;
    @NotEmpty
    private String startTime;
    private String endTime;
    @CreatedDate
    private Timestamp dateCreated;
    @LastModifiedDate
    private Timestamp dateUpdated;
    @Version
    private int version;

    public Watch() {
    }

    public Watch(int task, int parent, Date watchDate, String startTime) {
        this.task = task;
        this.parent = parent;
        this.watchDate = watchDate;
        this.startTime = startTime;
    }

}
