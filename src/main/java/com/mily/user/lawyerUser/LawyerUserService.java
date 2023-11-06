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

//    public Optional<LawyerUser> findByName (String userLoginId) {
//        return lawyerUserRepository.findByUserLoginId(userLoginId);
//    }
//
//    public Optional<LawyerUser> findByEmail (String userEmail) {
//        return lawyerUserRepository.findByEmail(userEmail);
//    }
//
//    public Optional<LawyerUser> findByPhoneNumber (String userPhoneNumber) {
//        return lawyerUserRepository.findByPhoneNumber(userPhoneNumber);
//    }

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

//    public LawyerUser getLawyer(String UserLoginId) {
//        Optional<LawyerUser> lawyerUser = lawyerUserRepository.findByUserLoginId(UserLoginId);
//        if (lawyerUser.isPresent()) {
//            return lawyerUser.get();
//        } else {
//            throw new Ut.DataNotFoundException("변호사 정보가 없습니다.");
//        }
//    }
}