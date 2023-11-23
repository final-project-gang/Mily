package com.mily.reservation;

import com.mily.standard.util.Ut;
import com.mily.user.LawyerUser;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequestMapping("/reservation")
@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final MilyUserService milyUserService;

    @PostMapping("")
    public String saveReservation(@ModelAttribute Reservation reservation, RedirectAttributes redirectAttributes, Long lawyerUserId) {
        MilyUser milyUser = milyUserService.getCurrentUser();

        if(!milyUser.getRole().equals("member")) {
            throw new Ut.DataNotFoundException("권한이 없습니다.");
        }

        LawyerUser lawyerUser = milyUserService.getLawyer(lawyerUserId).getLawyerUser();

        try {
            reservationService.saveReservation(milyUser, lawyerUser, reservation.getReservationTime());
            redirectAttributes.addFlashAttribute("message", "예약이 성공적으로 저장되었습니다.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "예약 저장 중 오류가 발생했습니다 : " + ex.getMessage());
        }
        return "redirect:/";
    }

    // 변호사 ID와 날짜에 따른 시간 선택 페이지
    @GetMapping("/available_times")
    public String getAvailableTimes(@RequestParam Long lawyerUserId, @RequestParam LocalDate date, Model model) {
        try {
            LawyerUser lawyerUser = milyUserService.getLawyer(lawyerUserId).getLawyerUser();
            List<LocalDateTime> availableTimes = reservationService.getAvailableTimes(lawyerUser, date);
            model.addAttribute("availableTimes", availableTimes);
            model.addAttribute("lawyerUserId", lawyerUserId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "예약 가능한 시간 조회 중 오류가 발생했습니다: " + ex.getMessage());
        }
        return "available_times";
    }

    // 변호사 ID에 따른 날짜 선택 페이지
    @GetMapping("/select_date")
    public String selectDate(@RequestParam("lawyerUserId") Long lawyerUserId, Model model) {
        MilyUser lawyerUser = milyUserService.findById(lawyerUserId).get();

        List<String> dates = new ArrayList<>();
        List<String> daysOfWeek = new ArrayList<>();
        LocalDate start = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

        for (int i = 0; i < 7; i++) {
            dates.add(start.plusDays(i).format(formatter));
            String dayOfWeek = start.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
            daysOfWeek.add(dayOfWeek);
        }

//        String dayOfWeek = ld.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);

        model.addAttribute("user", lawyerUser);
        model.addAttribute("lawyerUserId", lawyerUserId);
        model.addAttribute("dates", dates);
        model.addAttribute("day", daysOfWeek);

        return "select_date";
    }

    @GetMapping("reservation_list")
    public String getReservation(Model model) {
        List<Reservation> reservations = reservationService.getReservations(milyUserService.getCurrentUser().getId());
        model.addAttribute("reservations", reservations);
        return "reservation_list";
    }

    @PostMapping("/approveReservation/{id}")
    public String approveReservation(@PathVariable Long id, HttpServletRequest hsr) {
        String referer = hsr.getHeader("Referer");
        reservationService.approveReservation(reservationService.getReservation(id));
        return "redirect:" + referer;
    }

    @PostMapping("/refuseReservation/{id}")
    public String refuseReservation(@PathVariable Long id, HttpServletRequest hsr) {
        String referer = hsr.getHeader("Referer");
        reservationService.refuseReservation(reservationService.getReservation(id));
        return "redirect:" + referer;
    }
}