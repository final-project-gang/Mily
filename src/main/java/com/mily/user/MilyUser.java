package com.mily.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
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

    private String userDateOfBirth;

    private String userCreateDate;
}