package com.mily.lawyer;

import com.mily.appointment.Appointment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Lawyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

//    @ManyToOne
//    private Member author;

    @OneToMany(mappedBy = "lawyer", cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;
}
