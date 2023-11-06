package com.mily.user;

import com.mily.base.rsData.RsData;
import com.mily.estimate.Estimate;
import com.mily.estimate.EstimateRepository;
import com.mily.standard.util.Ut;
import com.mily.user.lawyerUser.LawyerUser;
import com.mily.user.lawyerUser.LawyerUserRepository;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public RsData<MilyUser> userSignup (String userLoginId, String userPassword, String userNickName, String userName, String userEmail, String userPhoneNumber, String userDateOfBirth, String area) {
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

        MilyUser mu = MilyUser.builder()
                .userLoginId(userLoginId)
                .userPassword(passwordEncoder.encode(userPassword))
                .userNickName(userNickName)
                .userName(userName)
                .userEmail(userEmail)
                .userPhoneNumber(userPhoneNumber)
                .userDateOfBirth(userDateOfBirth)
                .userCreateDate(now)
                .area(area)
                .build();

        mu = milyUserRepository.save(mu);
        return RsData.of("S-1", "MILY 회원이 되신 것을 환영합니다!", mu);
    }

    @Transactional
    public RsData<MilyUser> lawyerSignup(String userLoginId, String password, String name, String phoneNumber, String email, String major, String introduce, String area, String userDateOfBirth) {
        if (findByUserLoginId(userLoginId).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 사용 중인 아이디입니다.".formatted(userLoginId));
        }
        if (findByUserEmail(email).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 인증 된 이메일입니다.".formatted(email));
        }
        if (findByUserPhoneNumber(phoneNumber).isPresent()) {
            return RsData.of("F-1", "%s은(는) 이미 인증 된 전화번호입니다.".formatted(phoneNumber));
        }

        LocalDateTime now = LocalDateTime.now();

        MilyUser lu = MilyUser
                .builder()
                .userLoginId(userLoginId)
                .userPassword(passwordEncoder.encode(password))
                .userPhoneNumber(phoneNumber)
                .userName(name)
                .userEmail(email)
                .userCreateDate(now)
                .area(area)
                .userDateOfBirth(userDateOfBirth)
                .build();

        lu = milyUserRepository.save(lu);
        return RsData.of("S-1", "변호사 가입 신청을 완료하였습니다.", lu);
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

    @Transactional
    public void sendEstimate(String category, String categoryItem, MilyUser milyUser) {
        Estimate estimate = new Estimate();
        estimate.setCategory(category);
        estimate.setCategoryItem(categoryItem);
        estimate.setName(milyUser.getUserName());
        estimate.setBirth(milyUser.getUserDateOfBirth());
        estimate.setPhoneNumber(milyUser.getUserPhoneNumber());
        estimate.setMilyUser(milyUser);
        estimate.setArea(milyUser.getArea());
        estimate.setCreateDate(LocalDateTime.now());
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

//    public List<LawyerUser> getWaitingLawyerList() {
//        List<LawyerUser> lawyerUsers = lawyerUserRepository.findByCurrent("waiting");
//        if (lawyerUsers.isEmpty()) {
//            throw new Ut.DataNotFoundException("승인 대기중인 변호사 목록이 없습니다.");
//        }
//        return lawyerUsers;
//    }

//    @Transactional
//    public void approveLawyer(long id, String userLoginId) {
//        if (!isAdmin(userLoginId)) {
//            throw new Ut.UnauthorizedException("승인 권한이 없습니다.");
//        }
//
//        Optional<LawyerUser> optionalLawyer = lawyerUserRepository.findById(id);
//        if (optionalLawyer.isPresent()) {
//            LawyerUser lawyer = optionalLawyer.get();
//            if ("waiting".equals(lawyer.getCurrent())) {
//                lawyer.setCurrent("approve");
//                lawyerUserRepository.save(lawyer);
//            } else {
//                throw new Ut.InvalidDataException("선택된 변호사는 대기 중인 상태가 아닙니다.");
//            }
//        } else {
//            throw new Ut.DataNotFoundException("변호사를 찾을 수 없습니다.");
//        }
//    }

    public Optional<MilyUser> findUserByEmail(String userEmail) {
        return findByUserEmail(userEmail);
    }

    public MilyUser findUserLoginIdByEmail(String userEmail) {
        return milyUserRepository.findUserLoginIdByEmail(userEmail);
    }

    public Optional<MilyUser> findByuserLoginIdAndEmail(String userLoginId, String email) {
        return milyUserRepository.findByUserLoginIdAndUserEmail(userLoginId, email);
    }

    public static void sendTempPasswordToEmail(MilyUser member) {
    }

    public MilyUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User user) {
            return milyUserRepository.findByUserLoginId(user.getUsername()).orElse(null);
        }
        return null;
    }

    @Transactional
    public RsData<MilyUser> getPoint(MilyUser isLoginedUser, String orderName) {
        // isLoginedUser 의 milyPoint 값을 가져온다.
        int milyPoint = isLoginedUser.getMilyPoint();

        // 가져온 milyPoint 값에 orderName을 더한다.
        milyPoint += Integer.parseInt(orderName.substring(7));

        // repository에 저장한다.
        isLoginedUser.setMilyPoint(milyPoint);
        milyUserRepository.save(isLoginedUser);

        return RsData.of("S-1", "포인트 지급", null);
    }
}