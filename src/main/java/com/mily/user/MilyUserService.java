package com.mily.user;

import com.mily.base.rsData.RsData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public RsData<MilyUser> signup(String userLoginId, String userPassword, String userNickName, String userName, String userEmail, String userPhoneNumber, String userDateOfBirth) {
        if (findByUserLoginId(userLoginId).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 사용 중인 아이디입니다.".formatted(userLoginId));
        }
        if (findByUserNickName(userNickName).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 사용 중인 닉네임입니다.".formatted(userNickName));
        }
        if (findByUserEmail(userEmail).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 인증 된 이메일입니다.".formatted(userEmail));
        }
        if (findByUserPhoneNumber(userPhoneNumber).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 인증 된 전화번호입니다.".formatted(userPhoneNumber));
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        String nowDate = now.format(dtf);

        MilyUser mu = MilyUser.builder()
                .userLoginId(userLoginId)
                .userPassword(passwordEncoder.encode(userPassword))
                .userNickName(userNickName)
                .userName(userName)
                .userEmail(userEmail)
                .userPhoneNumber(userPhoneNumber)
                .userDateOfBirth(userDateOfBirth)
                .userCreateDate(nowDate)
                .build();

        mu = milyUserRepository.save(mu);
        return RsData.of("S-1", "MILY 회원이 되신 것을 환영합니다!", mu);
    }

    public Optional<MilyUser> findByUserLoginId(String userLoginId) {
        return milyUserRepository.findByUserLoginId(userLoginId);
    }

    public Optional<MilyUser> findByUserNickName(String userNickName) {
        return milyUserRepository.findByUserNickName(userNickName);
    }

    public Optional<MilyUser> findByUserEmail(String userEmail) {
        System.out.println("user33333 : " + milyUserRepository.findByUserEmail(userEmail));
        return milyUserRepository.findByUserEmail(userEmail);
    }

    public Optional<MilyUser> findByUserPhoneNumber(String userPhoneNumber) {
        return milyUserRepository.findByUserPhoneNumber(userPhoneNumber);
    }

    public Optional<MilyUser> findById(long id) {
        return milyUserRepository.findById(id);
    }

    public RsData checkUserLoginIdDup(String userLoginId) {
        if (findByUserLoginId(userLoginId).isPresent())
            return RsData.of("F-1", "%s(은)는 이미 사용 중인 아이디입니다.".formatted(userLoginId));

        return RsData.of("S-1", "%s(은)는 사용 가능한 아이디입니다.".formatted(userLoginId));
    }

    public RsData checkUserNickNameDup(String userNickName) {
        if (findByUserNickName(userNickName).isPresent())
            return RsData.of("F-1", "%s(은)는 이미 사용 중인 닉네임입니다.".formatted(userNickName));

        return RsData.of("S-1", "%s(은)는 사용 가능한 닉네임입니다.".formatted(userNickName));
    }

    public RsData checkUserEmailDup(String userEmail) {
        if (findByUserEmail(userEmail).isPresent())
            return RsData.of("F-1", "%s(은)는 이미 인증 된 이메일입니다.".formatted(userEmail));

        return RsData.of("S-1", "%s(은)는 사용 가능한 이메일입니다.".formatted(userEmail));
    }

    public RsData checkUserPhoneNumberDup(String userPhoneNumber) {
        if (findByUserPhoneNumber(userPhoneNumber).isPresent())
            return RsData.of("F-1", "%s(은)는 이미 인증 된 전화번호입니다.".formatted(userPhoneNumber));

        return RsData.of("S-1", "%s(은)는 사용 가능한 전화번호입니다.".formatted(userPhoneNumber));
    }

<<<<<<< HEAD
    public Optional<MilyUser> findUserByEmail(String userEmail) {
        return findByUserEmail(userEmail);
    }

    public Optional<String> findLoginIdByUserEmail(String userEmail) {
        Optional<MilyUser> user = findByUserEmail(userEmail);

        System.out.println("user22211 : " + user.map(MilyUser::getLoginId));
        return user.map(MilyUser::getLoginId);
    }

    public MilyUser findUserLoginIdByEmail(String userEmail) {
        return milyUserRepository.findUserLoginIdByEmail(userEmail);
    }

    public Optional<MilyUser> findByuserLoginIdAndEmail(String userLoginId, String email) {
        return milyUserRepository.findByUserLoginIdAndUserEmail(userLoginId, email);
    }


    public static void sendTempPasswordToEmail(MilyUser member) {
    }
}

=======
    public MilyUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User user) {
            return milyUserRepository.findByUserLoginId(user.getUsername()).orElse(null);
        }
        return null;
    }

    public RsData<MilyUser> getPoint (MilyUser isLoginedUser) {
        // isLoginedUser 의 milyPoint 값을 가져온다.
        int milyPoint = isLoginedUser.getMilyPoint();

        // 가져온 milyPoint 값에 orderName을 더한다.
        milyPoint += Integer.parseInt("밀리 포인트 200".substring(7));

        // repository에 저장한다.
        isLoginedUser.setMilyPoint(milyPoint);
        milyUserRepository.save(isLoginedUser);

        return RsData.of("S-1", "포인트 지급", null);
    }
}
>>>>>>> 1ce2ade47b663d41e46ec9ed2f6da9304acd870f
