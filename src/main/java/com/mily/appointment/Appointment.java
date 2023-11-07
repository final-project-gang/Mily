package com.mily.appointment;

import com.mily.lawyer.Lawyer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    //private LocalDateTime createDate;

    @ManyToOne
    private Appointment appointment;

    @ManyToOne
    private Lawyer lawyer;

}
