package com.mily;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MilyHomeController {

    @GetMapping("/")
    public String showMilyMain() {
        return "mily/mily_main";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/payment")
    public String doPayment() {
        return "mily/payment/test_payment";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/payment2")
    public String doPayment2() {
        return "mily/payment/test_payment2";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/success")
    public String paymentSuccess() {
        return "mily/payment/success";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/fail")
    public String paymentFail() {
        return "mily/payment/fail";
    }
}