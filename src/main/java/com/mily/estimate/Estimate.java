package com.mily.estimate;

import com.mily.user.milyUser.MilyUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String categoryItem;

    private String name;

    private String birth;

    private String phoneNumber;

    private LocalDateTime createDate;

    private String area;

    @OneToOne
    private MilyUser milyUser;
}