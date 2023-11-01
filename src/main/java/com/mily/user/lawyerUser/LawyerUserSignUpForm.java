package com.mily.user.lawyerUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LawyerUserSignUpForm {
    @NotEmpty(message = "아이디은 필수항목입니다.")
    private String userLoginId;

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String Password;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

    @NotEmpty(message = "전화번호는 필수항목입니다.")
    private String phoneNumber;

    private String organization;

    private String organizationNumber;

    private String major;

    private String introduce;

    private String area;
}