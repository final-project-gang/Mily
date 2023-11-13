package com.mily.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/emailForm")
    public String emailForm() {
        return "email-form"; // 위에서 정의한 HTML 폼의 이름
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String email, @RequestParam String subject, @RequestParam String message, RedirectAttributes redirectAttributes) {
        try {
            emailService.sendSimpleMessage(email, subject, message);
            redirectAttributes.addFlashAttribute("successMessage", "이메일이 성공적으로 전송되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "이메일 전송에 실패했습니다.");
        }
        return "redirect:/emailForm";
    }
}
