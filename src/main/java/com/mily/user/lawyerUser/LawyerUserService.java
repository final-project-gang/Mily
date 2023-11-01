package com.mily.user.lawyerUser;

import com.mily.base.rsData.RsData;
import com.mily.estimate.Estimate;
import com.mily.estimate.EstimateRepository;
import com.mily.standard.util.Ut;
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
public class LawyerUserService {
    private final EstimateRepository estimateRepository;
    private final LawyerUserRepository lawyerUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<LawyerUser> signup(String userLoginId, String password, String name, String phoneNumber, String email, String organization, String organizationNumber, String major, String introduce, String area) {
        if (findByName(userLoginId).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 사용 중인 아이디입니다.".formatted(userLoginId));
        }
        if (findByEmail(email).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 인증 된 이메일입니다.".formatted(email));
        }
        if (findByPhoneNumber(phoneNumber).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 인증 된 전화번호입니다.".formatted(phoneNumber));
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        String nowDate = now.format(dtf);

        LawyerUser lu = LawyerUser
                .builder()
                .userLoginId(userLoginId)
                .userPassword(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .name(name)
                .email(email)
                .major(major)
                .organization(organization)
                .organizationNumber(organizationNumber)
                .introduce(introduce)
                .current("waiting")
                .createDate(nowDate)
                .area(area)
                .build();

        lu = lawyerUserRepository.save(lu);
        return RsData.of("S-1", "변호사 가입 신청을 완료하였습니다.", lu);
    }

    public Optional<LawyerUser> findByName (String userLoginId) {
        return lawyerUserRepository.findByUserLoginId(userLoginId);
    }

    public Optional<LawyerUser> findByEmail (String userEmail) {
        return lawyerUserRepository.findByEmail(userEmail);
    }

    public Optional<LawyerUser> findByPhoneNumber (String userPhoneNumber) {
        return lawyerUserRepository.findByPhoneNumber(userPhoneNumber);
    }

    public List<Estimate> getEstimate(String category, String area) {
        List<Estimate> estimate = estimateRepository.findByCategoryAndArea(category, area);
        if(!estimate.isEmpty()) {
            return estimate;
        } else {
            List<Estimate> estimateArea = estimateRepository.findByArea(area);
            if(!estimateArea.isEmpty()) {
                return estimateArea;
            } else {
                List<Estimate> estimateCategory = estimateRepository.findByCategory(category);
                if(!estimateCategory.isEmpty()) {
                    return estimateCategory;
                }
                else {
                    throw new Ut.DataNotFoundException("견적서에 해당되는 변호사가 없습니다.");
                }
            }
        }
    }

    public LawyerUser getLawyer(String UserLoginId) {
        Optional<LawyerUser> lawyerUser = lawyerUserRepository.findByUserLoginId(UserLoginId);
        if (lawyerUser.isPresent()) {
            return lawyerUser.get();
        } else {
            throw new Ut.DataNotFoundException("변호사 정보가 없습니다.");
        }
    }
}