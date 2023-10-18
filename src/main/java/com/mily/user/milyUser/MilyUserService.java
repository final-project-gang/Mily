package com.mily.user.milyUser;

import com.mily.base.rsData.RsData;
import com.mily.estimate.Estimate;
import com.mily.estimate.EstimateRepository;
import com.mily.standard.util.Ut;
import com.mily.user.lawyerUser.LawyerUser;
import com.mily.user.lawyerUser.LawyerUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MilyUserService {
    private final MilyUserRepository milyUserRepository;
    private final EstimateRepository estimateRepository;
    private final LawyerUserRepository lawyerUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<MilyUser> signup (String userLoginId, String userPassword, String userNickName, String userName, String userEmail, String userPhoneNumber, String userDateOfBirth) {
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

        mu = milyUserRepository.save(mu);
        return RsData.of("S-1", "MILY 회원이 되신 것을 환영합니다!", mu);
    }

    public Optional<MilyUser> findByUserLoginId (String userLoginId) {
        return milyUserRepository.findByUserLoginId(userLoginId);
    }

    public Optional<MilyUser> findByUserNickName (String userNickName) {
        return milyUserRepository.findByUserNickName(userNickName);
    }

    public Optional<MilyUser> findByUserEmail (String userEmail) {
        return milyUserRepository.findByUserEmail(userEmail);
    }

    public Optional<MilyUser> findByUserPhoneNumber (String userPhoneNumber) {
        return milyUserRepository.findByUserPhoneNumber(userPhoneNumber);
    }

    public Optional<MilyUser> findById (long id) {
        return milyUserRepository.findById(id);
    }

    public RsData checkUserLoginIdDup (String userLoginId) {
        if ( findByUserLoginId(userLoginId).isPresent() ) return RsData.of("F-1", "%s(은)는 이미 사용 중인 아이디입니다.".formatted(userLoginId));

        return RsData.of("S-1", "%s(은)는 사용 가능한 아이디입니다.".formatted(userLoginId));
    }

    public RsData checkUserNickNameDup (String userNickName) {
        if ( findByUserNickName(userNickName).isPresent() ) return RsData.of("F-1", "%s(은)는 이미 사용 중인 닉네임입니다.".formatted(userNickName));

        return RsData.of("S-1", "%s(은)는 사용 가능한 닉네임입니다.".formatted(userNickName));
    }

    public RsData checkUserEmailDup (String userEmail) {
        if ( findByUserEmail(userEmail).isPresent() ) return RsData.of("F-1", "%s(은)는 이미 인증 된 이메일입니다.".formatted(userEmail));

        return RsData.of("S-1", "%s(은)는 사용 가능한 이메일입니다.".formatted(userEmail));
    }

    public RsData checkUserPhoneNumberDup (String userPhoneNumber) {
        if ( findByUserPhoneNumber(userPhoneNumber).isPresent() ) return RsData.of("F-1", "%s(은)는 이미 인증 된 전화번호입니다.".formatted(userPhoneNumber));

        return RsData.of("S-1", "%s(은)는 사용 가능한 전화번호입니다.".formatted(userPhoneNumber));
    }

    public void sendEstimate(String category, String categoryItem, MilyUser milyUser) {
        Estimate estimate = new Estimate();
        estimate.setCategory(category);
        estimate.setCategoryItem(categoryItem);
        estimate.setName(milyUser.getUserName());
        estimate.setBirth(milyUser.getUserDateOfBirth());
        estimate.setPhoneNumber(milyUser.getUserPhoneNumber());
        estimate.setMilyUser(milyUser);
        this.estimateRepository.save(estimate);
    }

    public MilyUser getUser(String userName) {
        Optional<MilyUser> milyUser = milyUserRepository.findByUserName(userName);
        if (milyUser.isPresent()) {
            return milyUser.get();
        } else {
            throw new Ut.DataNotFoundException("유저 정보가 없습니다.");
        }
    }

    public boolean isAdmin(String userLoginId) {
        return milyUserRepository.findByUserLoginId(userLoginId)
                .map(MilyUser::getUserLoginId)
                .filter(loginId -> loginId.equals("admin123"))
                .isPresent();
    }

    public List<LawyerUser> getWaitingLawyerList() {
        List<LawyerUser> lawyerUsers = lawyerUserRepository.findByCurrent("waiting");
        if (lawyerUsers.isEmpty()) {
            throw new Ut.DataNotFoundException("승인 대기중인 변호사 목록이 없습니다.");
        }
        return lawyerUsers;
    }
}