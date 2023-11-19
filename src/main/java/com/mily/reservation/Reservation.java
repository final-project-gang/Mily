package com.mily.reservation;

import java.time.LocalDateTime;
import com.mily.user.LawyerUser;
import com.mily.user.MilyUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@Component
@DynamicInsert
@DynamicUpdate
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime reservationTime;

    @ManyToOne
    private MilyUser milyUser;

    @ManyToOne
    private LawyerUser lawyerUser;
}