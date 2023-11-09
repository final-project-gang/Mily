package com.mily.user;

import com.mily.base.rq.Rq;
import com.mily.base.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.Optional;

=======
import java.security.Principal;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
>>>>>>> 2d6423cc72e2a42843ef688d4d7e1c14dbe22566

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class MilyUserController {
    private final Rq rq;
    private final MilyUserService milyUserService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String showUserLogin() {
        return "mily/milyuser/login_form";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/lawyerLogin")
    public String showLawyerLogin() {
        return "mily/milyuser/lawyer_login_form";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signup")
    public String showSignup() {
        return "mily/milyuser/signup_form";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public String doSignup(@Valid SignupForm signupForm) {
        RsData<MilyUser> signupRs = milyUserService.userSignup(
                signupForm.getUserLoginId(),
                signupForm.getUserPassword(),
                signupForm.getUserNickName(),
                signupForm.getUserName(),
                signupForm.getUserEmail(),
                signupForm.getUserPhoneNumber(),
                signupForm.getUserDateOfBirth(),
                signupForm.getArea()
        );

        if (signupRs.isFail()) {
            rq.historyBack(signupRs.getMsg());
            return "common/js";
        }

        return rq.redirect("/", signupRs.getMsg());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/lawyerSignup")
    public String showLawyerSignup() { return "mily/milyuser/lawyer_signup_form"; }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/lawyerSignup")
    public String doLawyerSignup(@Valid LawyerUser lawyerUser, @Valid SignupForm signupForm) {
        RsData<MilyUser> signupRs1 = milyUserService.userSignup(
                signupForm.getUserLoginId(),
                signupForm.getUserPassword(),
                signupForm.getUserNickName(),
                signupForm.getUserName(),
                signupForm.getUserEmail(),
                signupForm.getUserPhoneNumber(),
                signupForm.getUserDateOfBirth(),
                signupForm.getArea()
        );

        MilyUser milyUser = signupRs1.getData();

        RsData<LawyerUser> signupRs2 = milyUserService.lawyerSignup(
                lawyerUser.getMajor(),
                lawyerUser.getIntroduce(),
                lawyerUser.getOfficeAddress(),
                lawyerUser.getLicenseNumber(),
                lawyerUser.getArea(),
                milyUser
        );

        if (signupRs2.isFail()) {
            rq.historyBack(signupRs2.getMsg());
            return "common/js";
        }

        return rq.redirect("/", signupRs2.getMsg());
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

        @NotBlank
        private String area;
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
        String userName = principal.getName();
        MilyUser milyUser = milyUserService.getUser(userName);
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

    @GetMapping("/waitLawyerList")
    public String getWaitingLawyerList(Principal principal, Model model) {
        String userLoginId = principal.getName();
        if (milyUserService.isAdmin(userLoginId)) {
            List<MilyUser> waitingLawyers = milyUserService.getWaitingLawyerList();
            model.addAttribute("waitingLawyers", waitingLawyers);
            return "/mily/waiting_lawyer_list";
        } else {
            return "mily_main";
        }
    }

    @PostMapping("/approveLawyer/{id}")
    public String approveLawyer(@PathVariable int id, Principal principal) {
        String adminLoginId = principal.getName();
        milyUserService.approveLawyer(id, adminLoginId);
        return "redirect:/user/waitLawyerList";
    }

    // 아이디 찾기 페이지를 보여주는 핸들러
    @PreAuthorize("isAnonymous()")
    @GetMapping("/findId")
    public String showFindId() {
        return "mily/milyuser/find_id_form";  // 해당 페이지의 경로와 이름을 알맞게 수정하세요.
    }

    // 비밀번호 찾기 페이지를 보여주는 핸들러
    @PreAuthorize("isAnonymous()")
    @GetMapping("/findPassword")
    public String showFindPassword() {
        return "mily/milyuser/find_password_form";
    }

<<<<<<< HEAD
=======
    @PreAuthorize("isAnonymous()")
    @PostMapping("/findPassword")
    public String findPassword(@RequestParam String userLoginId, @RequestParam String email, RedirectAttributes redirectAttributes) {
        // findByUsernameAndEmail을 호출하여 사용자를 찾습니다.
        return milyUserService.findByuserLoginIdAndEmail(userLoginId, email)
                .map(member -> {
                    // 임시 비밀번호 발송 로직을 실행합니다.
                    milyUserService.sendTempPasswordToEmail(member);
                    // 성공 메시지와 함께 로그인 페이지로 리다이렉트합니다.
                    redirectAttributes.addFlashAttribute("message", "해당 회원의 이메일로 임시 비밀번호를 발송하였습니다.");
                    return "redirect:/user/login?lastUsername=" + member.getUserLoginId();
                })
                .orElseGet(() -> {
                    // 사용자를 찾을 수 없을 경우 에러 메시지를 설정하고 이전 페이지로 이동합니다.
                    redirectAttributes.addFlashAttribute("errorMessage", "일치하는 회원이 존재하지 않습니다.");
                    return "redirect:/mily/milyuser/find_password_form";
                });
    }
>>>>>>> 2d6423cc72e2a42843ef688d4d7e1c14dbe22566

    @PostMapping("/retrieveId")
    public String retrieveId(@RequestParam String userEmail, Model model, RedirectAttributes redirectAttributes) {
        MilyUser milyUser = milyUserService.findUserLoginIdByEmail(userEmail);
        model.addAttribute("foundId", milyUser.getUserLoginId());
        return "mily/milyuser/retrieve_id_result";
    }

    @PostMapping("/retrievePassword")
    public String retrievePassword(@RequestParam String userEmail, @RequestParam String userLoginId, RedirectAttributes redirectAttributes) {
        Optional<MilyUser> optionalUser = milyUserService.findByUserLoginIdAndEmail(userLoginId, userEmail);

        if (optionalUser.isPresent()) {
            MilyUser milyUser = optionalUser.get();
            // 임시 비밀번호 생성 및 저장 로직
            String tempPassword = milyUserService.generateTempPassword();
            milyUserService.updateUserPassword(milyUser.getId(), tempPassword);

            // 임시 비밀번호 이메일 발송 로직
            milyUserService.sendTempPasswordToEmail(milyUser.getEmail(), tempPassword);

            // 성공 메시지를 리다이렉트 애트리뷰트에 추가
            redirectAttributes.addFlashAttribute("message", "임시 비밀번호를 이메일로 발송하였습니다.");
            return "redirect:/user/login";
        } else {
            // 에러 메시지를 리다이렉트 애트리뷰트에 추가
            redirectAttributes.addFlashAttribute("errorMessage", "일치하는 사용자 정보가 없습니다.");
            return "redirect:/mily/milyuser/find_password_form";
        }
    }


    @PostMapping("/findLoginIdPage")
    public ResponseEntity<String> retrieveId(@RequestParam("userEmail") String userEmail) {
        MilyUser milyUser = milyUserService.findUserLoginIdByEmail(userEmail);
        if (milyUser.getUserEmail().equals(userEmail)) {
            return ResponseEntity.ok("");
        } else {
            return ResponseEntity.badRequest().body("아이디를 찾을 수 없습니다.");
        }
    }
<<<<<<< HEAD
}

=======
}
>>>>>>> 2d6423cc72e2a42843ef688d4d7e1c14dbe22566
