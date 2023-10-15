package com.mily.user.lawyerUser;

import com.mily.base.rq.Rq;
import com.mily.base.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lawyer")
@RequiredArgsConstructor
@Controller
public class LawyerUserController {
    private final Rq rq;
    private final LawyerUserService lawyerUserService;

    @GetMapping("/signup")
    public String showLawyerSignup() { return "mily/lawyeruser/signup_form"; }

    @PostMapping("/signup")
    public String doLawterSignup(@Valid LawyerUserSignUpForm lawyerUserSignUpForm) {
        RsData<LawyerUser> signupRs = lawyerUserService.join(
                lawyerUserSignUpForm.getName(),
                lawyerUserSignUpForm.getPassword(),
                lawyerUserSignUpForm.getPhoneNumber(),
                lawyerUserSignUpForm.getEmail(),
                lawyerUserSignUpForm.getOrganization(),
                lawyerUserSignUpForm.getOrganizationNumber(),
                lawyerUserSignUpForm.getMajor(),
                lawyerUserSignUpForm.getIntroduce()
        );

        if(signupRs.isFail()) {
            rq.historyBack(signupRs.getMsg());
            return "common/js";
        }

        return rq.redirect("/", signupRs.getMsg());
    }
}
