package com.mily.reservation;

import com.mily.user.LawyerUser;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MilyUserService milyUserService;

    // 예약 저장
    public void saveReservation(MilyUser milyUser, LawyerUser lawyerUser, LocalDateTime time) {
        Reservation reservation = new Reservation();
        reservation.setMilyUser(milyUser);
        reservation.setLawyerUser(lawyerUser);
        reservation.setReservationTime(time);
        reservation.setStatus("waiting");
        reservationRepository.save(reservation);
    }

    // 예약 거절
    public void refuseReservation(Reservation reservation) {
        reservation.setStatus("refuse");
        reservationRepository.save(reservation);
    }

    public void approveReservation(Reservation reservation) {
        reservation.setStatus("approve");
        reservationRepository.save(reservation);
    }

    // 예약 가능한 시간 표시
    public List<LocalDateTime> getAvailableTimes(LawyerUser lawyerUser, LocalDate reservationTime) {
        List<LocalDateTime> availableTimes = new ArrayList<>();
        for (int hour = 9; hour < 18; hour++) {
            LocalDateTime dateTime = reservationTime.atTime(hour, 0);
            List<Reservation> overlappingReservations = reservationRepository.findByLawyerUserAndReservationTimeBetween(
                    lawyerUser, dateTime.minusHours(1), dateTime);
            if (overlappingReservations.isEmpty()) {
                availableTimes.add(dateTime);
            }
        }
        return availableTimes;
    }

    public Reservation getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow();
    }

    public List<Reservation> getReservations(long lawyerUserId) {
        return reservationRepository.findByLawyerUserId(lawyerUserId);
    }
}