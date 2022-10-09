package com.fareye.training.model;

import com.fareye.training.utility.EncryptionUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
public class User {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    private Boolean isVerified;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @NotNull
    private String password;

    private  String role;

    private Boolean isActive;

    private String GithubUserName;

    private String GithubPhoto;

    public void setPassword(String password) {
        this.password = EncryptionUtil.encodePassword(password);
    }
}
