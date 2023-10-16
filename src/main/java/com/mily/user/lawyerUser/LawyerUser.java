package com.mily.user.lawyerUser;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawyerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @Email
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String organization;

    private String organizationNumber;

    private String major;

    private String introduce;

    private String current;
}