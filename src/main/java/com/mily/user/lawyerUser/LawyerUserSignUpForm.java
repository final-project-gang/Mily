package com.mily.user.lawyerUser;

import com.mily.user.MilyUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class LawyerUserSignUpForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String major;

    @NotBlank
    private String introduce;

    @NotBlank
    private String officeAddress;

    @NotBlank
    private String licenseNumber;

    @OneToOne
    @MapsId
    private MilyUser milyUser;
}