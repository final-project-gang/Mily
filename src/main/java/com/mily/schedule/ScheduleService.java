package com.mily.schedule;

import com.mily.reservation.Reservation;
import com.mily.reservation.ReservationRepository;
import com.mily.user.LawyerUser;
import com.mily.user.LawyerUserRepository;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;
    private final LawyerUserRepository lawyerUserRepository;
    private final MilyUserRepository milyUserRepository;

    public void reserveConsultation(MilyUser milyUser, LawyerUser lawyerUser, LocalDateTime reserveTime) {
        for (Schedule schedule : lawyerUser.getSchedules()) {
            if ((schedule.getScheduleStartTime().isBefore(reserveTime) || schedule.getScheduleStartTime().isEqual(reserveTime)) &&
                    (schedule.getScheduleEndTime().isAfter(reserveTime))) {
                throw new RuntimeException("The lawyer is not available at the reserve time.");
            }
        }

        Reservation reservation = new Reservation();
        reservation.setMilyUser(milyUser);
        reservation.setLawyerUser(lawyerUser);
        reservation.setReservationTime(reserveTime);
        reservationRepository.save(reservation);
    }

    public void acceptReserve(LawyerUser lawyerUser, Reservation reservation) {
        if (!reservation.getLawyerUser().equals(lawyerUser)) {
            throw new RuntimeException("The request is not for this lawyer.");
        }

        Schedule schedule = new Schedule();
        schedule.setLawyerUser(lawyerUser);
        schedule.setScheduleStartTime(reservation.getReservationTime());
        schedule.setScheduleEndTime(reservation.getReservationTime().plusHours(1)); // Assuming the consultation takes 1 hour
        scheduleRepository.save(schedule);
    }

    public List<LawyerUser> getAvailableLawyers(LocalDateTime reserveTime) {
        List<LawyerUser> availableLawyers = new ArrayList<>();

        DayOfWeek day = reserveTime.getDayOfWeek();
        int hour = reserveTime.getHour();

        if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY) ||
                hour < 9 || (hour > 13 && hour < 14) || hour >= 18) {
            throw new RuntimeException("The requested time is not within the allowed range.");
        }

        List<LawyerUser> allLawyers = lawyerUserRepository.findAll();

        for (LawyerUser lawyerUser : allLawyers) {
            boolean isAvailable = true;
            for (Schedule schedule : lawyerUser.getSchedules()) {
                if ((schedule.getScheduleStartTime().isBefore(reserveTime) || schedule.getScheduleStartTime().isEqual(reserveTime)) &&
                        (schedule.getScheduleEndTime().isAfter(reserveTime))) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableLawyers.add(lawyerUser);
            }
        }

        return availableLawyers;
    }

    public List<Schedule> getSchedule(LawyerUser lawyerUser) {
        return scheduleRepository.findByLawyerUser(lawyerUser);
    }

    public void rejectRequest(LawyerUser lawyerUser, Reservation reservation) {
        if (!reservation.getLawyerUser().equals(lawyerUser)) {
            throw new RuntimeException("The request is not for this lawyer.");
        }
        reservationRepository.delete(reservation);
    }

    public List<LocalDateTime> getAvailableTimes(LawyerUser lawyerUser, LocalDate date) {
        List<LocalDateTime> availableTimes = new ArrayList<>();

        // Define the working hours
        List<LocalTime> workingHours = IntStream.range(9, 18)
                .filter(i -> i != 14)
                .mapToObj(i -> LocalTime.of(i, 0))
                .collect(Collectors.toList());

        // Get the lawyer's schedules on the given date
        List<Schedule> schedules = lawyerUser.getSchedules().stream()
                .filter(s -> s.getScheduleStartTime().toLocalDate().equals(date))
                .collect(Collectors.toList());

        // Check each working hour
        for (LocalTime time : workingHours) {
            LocalDateTime dateTime = LocalDateTime.of(date, time);

            boolean isAvailable = true;
            for (Schedule schedule : schedules) {
                if (!schedule.getScheduleStartTime().isAfter(dateTime) && !schedule.getScheduleEndTime().isBefore(dateTime)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableTimes.add(dateTime);
            }
        }

        return availableTimes;
    }

    public Reservation makeReservation(Long userId, Long lawyerId, LocalDateTime time) {
        // Get the user and the lawyer from the repository
        MilyUser milyUser = milyUserRepository.findById(userId).orElseThrow();
        LawyerUser lawyerUser = lawyerUserRepository.findById(lawyerId).orElseThrow();

        // Check if the lawyer is available at the requested time
        if (!getAvailableTimes(lawyerUser, time.toLocalDate()).contains(time)) {
            throw new RuntimeException("The lawyer is not available at the requested time.");
        }

        // Create a new reservation
        Reservation reservation = new Reservation();
        reservation.setMilyUser(milyUser);
        reservation.setLawyerUser(lawyerUser);
        reservation.setReservationTime(time);

        // Save the reservation in the repository
        return reservationRepository.save(reservation);
    }
}
