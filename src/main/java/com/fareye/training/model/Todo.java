package com.fareye.training.model;

import com.fareye.training.annotation.TodoTitleConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter @Setter @TodoTitleConstraint
public class Todo {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;

    @NotNull
    private String body;

    @NotNull
    private String title;

    @Value("Incomplete")
    private String status;

    private User user;

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = LocalDateTime.now();
    }
}
