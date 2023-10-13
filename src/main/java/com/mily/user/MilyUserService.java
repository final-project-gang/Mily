package com.mily.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MilyUserService {
    private final MilyUserRepository milyUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MilyUser signup (String userLoginId, String userPassword, String userNickName, String userName, String userEmail, String userPhoneNumber, String userDateOfBirth) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        String nowDate = now.format(dtf);

        MilyUser mu = MilyUser
                .builder()
                .userLoginId(userLoginId)
                .userPassword(passwordEncoder.encode(userPassword))
                .userNickName(userNickName)
                .userName(userName)
                .userEmail(userEmail)
                .userPhoneNumber(userPhoneNumber)
                .userDateOfBirth(userDateOfBirth)
                .userCreateDate(nowDate)
                .build();

        return milyUserRepository.save(mu);
    }

    public Optional<MilyUser> findByUserLoginId (String userLoginId) {
        return milyUserRepository.findByUserLoginId(userLoginId);
    }

    public Optional<MilyUser> findById (long id) {
        return milyUserRepository.findById(id);
    }
}