package com.fareye.training.model;

import com.fareye.training.Annotation.TodoTitleConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter @Setter @TodoTitleConstraint
public class Todo {
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dueDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date modifiedDate;

    private String body;

    private String title;

    private String status;

    private User user;

}
