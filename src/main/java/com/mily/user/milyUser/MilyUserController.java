package com.mily.user.milyUser;

import com.mily.base.rq.Rq;
import com.mily.base.rsData.RsData;
import com.mily.user.lawyerUser.LawyerUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class MilyUserController {
    private final Rq rq;
    private final MilyUserService milyUserService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String showLogin() {
        return "mily/milyuser/login_form";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signup")
    public String showSignup() {
        return "mily/milyuser/signup_form";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public String doSignup(@Valid SignupForm signupForm) {
        RsData<MilyUser> signupRs = milyUserService.signup(signupForm.getUserLoginId(), signupForm.getUserPassword(), signupForm.getUserNickName(), signupForm.getUserName(), signupForm.getUserEmail(), signupForm.getUserPhoneNumber(), signupForm.getUserDateOfBirth());

        if (signupRs.isFail()) {
            rq.historyBack(signupRs.getMsg());
            return "common/js";
        }

        return rq.redirect("/", signupRs.getMsg());
    }

    @Getter
    @AllArgsConstructor
    public static class SignupForm {
        @NotBlank
        private String userLoginId;

        @NotBlank
        private String userPassword;

        @NotBlank
        private String userNickName;

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

    @GetMapping("checkUserLoginIdDup")
    @ResponseBody
    public RsData checkUserLoginIdDup(String userLoginId) {
        return milyUserService.checkUserLoginIdDup(userLoginId);
    }

    @GetMapping("checkUserNickNameDup")
    @ResponseBody
    public RsData checkUserNickName(String userNickName) {
        return milyUserService.checkUserNickNameDup(userNickName);
    }

    @GetMapping("checkUserEmailDup")
    @ResponseBody
    public RsData checkUserEmail(String userEmail) {
        return milyUserService.checkUserEmailDup(userEmail);
    }

    @GetMapping("checkUserPhoneNumberDup")
    @ResponseBody
    public RsData checkUserPhoneNumber(String userPhoneNumber) {
        return milyUserService.checkUserPhoneNumberDup(userPhoneNumber);
    }

    @GetMapping("/estimate")
    public String showForm(EstimateCreateForm estimateCreateForm) {
        return "estimate";
    }

    @PostMapping("/estimate")
    public String getEstimate(@Valid EstimateCreateForm estimateCreateForm, Principal principal) {
        String name = principal.getName();
        MilyUser milyUser = milyUserService.getUser(name);
        milyUserService.sendEstimate(estimateCreateForm.getCategory(), estimateCreateForm.getCategoryItem(), milyUser);
        return rq.redirect("/", "견적서가 전달되었습니다.");
    }

    @Getter
    @AllArgsConstructor
    public class EstimateCreateForm {
        @NotEmpty(message = "카테고리 선택은 필수입니다.")
        private String category;

        @NotEmpty(message = "상세 항목은 필수입니다.")
        private String categoryItem;
    }

    @GetMapping("/waitlawyerlist")
    public String getWaitingLawyerList(Principal principal, Model model) {
        String userLoginId = principal.getName();
        if (milyUserService.isAdmin(userLoginId)) {
            List<LawyerUser> waitingLawyers = milyUserService.getWaitingLawyerList();
            model.addAttribute("waitingLawyers", waitingLawyers);
            return "/mily/waiting_lawyer_list";
        } else {
            return "mily_main";
        }
    }

    @PostMapping("/approvelawyer/{id}")
    public String approveLawyer(@PathVariable int id, Principal principal) {
        String adminLoginId = principal.getName();
        this.milyUserService.approveLawyer(id, adminLoginId);
        return "redirect:/waitlawyerslist";
    }
}