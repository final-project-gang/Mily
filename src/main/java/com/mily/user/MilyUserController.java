package com.mily.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class MilyUserController {
    private final MilyUserService milyUserService;

    @GetMapping("/signup")
    public String showSignup() {
        return "mily/milyuser/signup_form";
    }

    @PostMapping("/signup")
    public String doSignup(@Valid SignupForm signupForm) {
        milyUserService.signup(signupForm.getUserLoginId(), signupForm.getUserPassword(), signupForm.getUserNickname(), signupForm.getUserName(), signupForm.getUserEmail(), signupForm.getUserPhoneNumber(), signupForm.getUserDateOfBirth());
        return "redirect:/?msg=";
    }

    @Getter
    @AllArgsConstructor
    public static class SignupForm {
        @NotBlank
//        @Size(min = 4, max = 12)
        private String userLoginId;

        @NotBlank
        private String userPassword;

//        @NotBlank
//        private String userPassword2;

        @NotBlank
        private String userNickname;

        @NotBlank
        private String userName;

        @NotBlank
        @Email
        private String userEmail;

        @NotBlank
        private String userPhoneNumber;

        @NotBlank
        private String userDateOfBirth;
    }
}