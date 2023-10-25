package com.mily.user.lawyerUser;

import com.mily.base.rq.Rq;
import com.mily.base.rsData.RsData;
import com.mily.estimate.Estimate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequestMapping("/lawyer")
@RequiredArgsConstructor
@Controller
public class LawyerUserController {
    private final Rq rq;
    private final LawyerUserService lawyerUserService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String showLogin() {
        return "mily/lawyeruser/login_form";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signup")
    public String showLawyerSignup() { return "mily/lawyeruser/signup_form"; }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public String doLawyerSignup(@Valid LawyerUserSignUpForm lawyerUserSignUpForm) {
        RsData<LawyerUser> signupRs = lawyerUserService.signup(
                lawyerUserSignUpForm.getName(),
                lawyerUserSignUpForm.getPassword(),
                lawyerUserSignUpForm.getPhoneNumber(),
                lawyerUserSignUpForm.getEmail(),
                lawyerUserSignUpForm.getOrganization(),
                lawyerUserSignUpForm.getOrganizationNumber(),
                lawyerUserSignUpForm.getMajor(),
                lawyerUserSignUpForm.getIntroduce(),
                lawyerUserSignUpForm.getArea()
        );

        if(signupRs.isFail()) {
            rq.historyBack(signupRs.getMsg());
            return "common/js";
        }

        return rq.redirect("/", signupRs.getMsg());
    }

    @GetMapping("/getEstimate")
    public String getEstimate(Principal principal, Model model) {
        String lawyerName = principal.getName();
        LawyerUser lawyerUser = lawyerUserService.getLawyer(lawyerName);
        String category = lawyerUser.getMajor();
        String area = lawyerUser.getArea();
        List<Estimate> estimates = lawyerUserService.getEstimate(category, area);
        model.addAttribute("estimates", estimates);
        return "estimate_list";
    }
}