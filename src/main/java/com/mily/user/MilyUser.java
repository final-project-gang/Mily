package com.mily.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class MilyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String userLoginId;
    private String userPassword;
    @Column(unique = true)
    private String userNickName;
    private String userName;
    @Column(unique = true)
    private String userEmail;
    @Column(unique = true)
    private String userPhoneNumber;
    private LocalDate userDateOfBirth;
}