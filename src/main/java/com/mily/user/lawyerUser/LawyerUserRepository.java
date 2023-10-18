package com.mily.user.lawyerUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LawyerUserRepository extends JpaRepository<LawyerUser, Long> {
    Optional<LawyerUser> findByName(String name);
    Optional<LawyerUser> findByCurrent(String current);
}
