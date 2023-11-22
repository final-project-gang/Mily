package com.mily.reservation;

import com.mily.standard.util.Ut;
import com.mily.user.LawyerUser;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping("/{reservationId}/refuse")
    public String refuseReservation(@PathVariable Long reservationId, RedirectAttributes redirectAttributes) {
        try {
            Reservation reservation = reservationService.getReservation(reservationId);
            reservationService.refuseReservation(reservation);
            redirectAttributes.addFlashAttribute("message", "예약이 성공적으로 거절되었습니다.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "예약 거절 중 오류가 발생했습니다: " + ex.getMessage());
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
        model.addAttribute("lawyerUserId", lawyerUserId);
        return "select_date";
    }
}