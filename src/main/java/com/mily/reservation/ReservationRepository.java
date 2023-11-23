package com.mily.reservation;

import com.mily.user.LawyerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByLawyerUserAndReservationTimeBetween(LawyerUser lawyerUser, LocalDateTime startTime, LocalDateTime endTime);
    List<Reservation> findByLawyerUserId(Long Id);
}