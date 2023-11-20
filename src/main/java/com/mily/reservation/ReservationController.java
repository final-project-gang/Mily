package com.mily.reservation;

import com.mily.user.LawyerUser;
import com.mily.user.LawyerUserRepository;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/reservation")
@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final MilyUserService milyUserService;

    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveReservation(@ModelAttribute Reservation reservation, RedirectAttributes redirectAttributes, @RequestParam("lawyerUserId") Long lawyerUserId, Model model) {
        MilyUser milyUser = milyUserService.getCurrentUser();
        LawyerUser lawyerUser = milyUserService.getLawyer(lawyerUserId).getLawyerUser();
        model.addAttribute("lawyerUserId", lawyerUserId);
        try {
            reservationService.saveReservation(milyUser, lawyerUser, reservation.getReservationTime());
            redirectAttributes.addFlashAttribute("message", "예약이 성공적으로 저장되었습니다.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "예약 저장 중 오류가 발생했습니다: " + ex.getMessage());
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

    @GetMapping("/available_times")
    public String getAvailableTimes(@RequestParam Long lawyerUserId, @RequestParam LocalDate date, Model model) {
        try {
            LawyerUser lawyerUser = milyUserService.getLawyer(lawyerUserId).getLawyerUser();
            List<LocalDateTime> availableTimes = reservationService.getAvailableTimes(lawyerUser, date);
            model.addAttribute("availableTimes", availableTimes);
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