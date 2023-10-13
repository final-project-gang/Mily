package com.mily.user.lawyerUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LawyerUserService {
    private final LawyerUserRepository lawyerUserRepository;

    public void join(String name, String phoneNumber, String email, String organization, String organizationNumber, String major, String introduce) {
        LawyerUser lawyerUser = new LawyerUser();
        lawyerUser.setName(name);
        lawyerUser.setPhoneNumber(phoneNumber);
        lawyerUser.setEmail(email);
        lawyerUser.setOrganization(organization);
        lawyerUser.setOrganizationNumber(organizationNumber);
        lawyerUser.setMajor(major);
        lawyerUser.setIntroduce(introduce);
        this.lawyerUserRepository.save(lawyerUser.getId());
    }
}
