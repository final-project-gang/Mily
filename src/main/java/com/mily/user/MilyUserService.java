package com.mily.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MilyUserService {
    private final MilyUserRepository milyUserRepository;
    private final PasswordEncoder passwordEncoder;

    public MilyUser signUp (String userName, String userPassword, String userNickName, String userEmail, String userPhoneNumber) {
        MilyUser milyUser = new MilyUser();
        milyUser.setUserName(userName);
        milyUser.setUserPassword(passwordEncoder.encode(userPassword));
        // BCryptPasswordEncoder 클래스를 사용해서 비밀번호를 암호화하여 저장
        milyUser.setUserNickName(userNickName);
        milyUser.setUserPhoneNumber(userPhoneNumber);

        return milyUser;
    }
}