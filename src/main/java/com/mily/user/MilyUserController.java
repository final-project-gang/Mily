package com.mily.user;

import com.mily.base.rq.Rq;
import com.mily.base.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


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
}

