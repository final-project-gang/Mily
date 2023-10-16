package com.mily.user.lawyerUser;

import com.mily.base.rsData.RsData;
import com.mily.user.milyUser.MilyUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LawyerUserService {
    private final LawyerUserRepository lawyerUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<LawyerUser> join(String name, String password, String phoneNumber, String email, String organization, String organizationNumber, String major, String introduce) {
        LawyerUser lu = LawyerUser
                .builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .email(email)
                .major(major)
                .organization(organization)
                .organizationNumber(organizationNumber)
                .introduce(introduce)
                .build();

        lu = lawyerUserRepository.save(lu);
        return RsData.of("S-1", "변호사 가입 신청을 완료하였습니다.", lu);
    }

    public Optional<LawyerUser> findByName(String name) {
        return lawyerUserRepository.findByName(name);
    }
}