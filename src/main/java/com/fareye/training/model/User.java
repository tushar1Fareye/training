package com.fareye.training.model;

import com.fareye.training.utility.EncryptionUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    private Boolean isVerified;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @NotNull
    private String password;

    @NotNull
    private  String role;

    private Boolean isActive;

    private String githubUserName;

    private String githubToken;

    private String githubPhoto;

    public void setCreated(LocalDateTime created) {
        this.created = LocalDateTime.now();
    }

    public void setPassword(String password) {
        this.password = EncryptionUtil.encodePassword(password);
    }
}
