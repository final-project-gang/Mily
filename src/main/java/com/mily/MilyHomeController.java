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
}