package com.mily.appointment;

import ch.qos.logback.core.model.Model;
import com.mily.lawyer.Lawyer;
import com.mily.lawyer.LawyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("/appointment")
@RequiredArgsConstructor
@Controller
public class AppointmentController {

    private final LawyerService lawyerService;
    private final AppointmentService appointmentService;

    @PostMapping("/create/{id}")
    public String createAppointment(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        Lawyer lawyer = this.lawyerService.getLawyer(id);
        //답변 저장
        this.appointmentService.create(lawyer, content);
        return String.format("redirect:/lawyer/deail/%s", id);
    }
}
