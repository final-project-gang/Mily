package com.mily.schedule;

import com.mily.reservation.Reservation;
import com.mily.user.LawyerUser;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/reserve")
@RequiredArgsConstructor
@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final MilyUserService milyUserService;

    @GetMapping("/lawyers/{lawyerId}/available-times")
    public List<LocalDateTime> getAvailableTimes(@PathVariable Long lawyerId, @RequestParam LocalDate date) {
        MilyUser milyUser = milyUserService.getLawyer(lawyerId); // Assuming that there is a method to get a lawyer by ID
        LawyerUser lawyerUser = milyUserService.isLawyer(milyUser);
        return scheduleService.getAvailableTimes(lawyerUser, date);
    }

    // Make a reservation
    @PostMapping("/reservations")
    public Reservation makeReservation(@RequestBody Reservation reservation) {
        return scheduleService.makeReservation(reservation.getMilyUser().getId(), reservation.getLawyerUser().getId(), reservation.getReservationTime());
        // Assuming that there is a method to make a reservation and ReservationRequest is a DTO that contains necessary information
    }
}