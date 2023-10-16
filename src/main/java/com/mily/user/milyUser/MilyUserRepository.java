package com.mily.user.milyUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MilyUserRepository extends JpaRepository<MilyUser, Long> {
    Optional<MilyUser> findByUserLoginId (String userLoginId);
    Optional<MilyUser> findByUserNickName (String userNickName);
    Optional<MilyUser> findByUserEmail (String userEmail);
    Optional<MilyUser> findByUserPhoneNumber (String userPhoneNumber);
    Optional<MilyUser> findByUserName(String userName);
}