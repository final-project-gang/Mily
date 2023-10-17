package com.mily.user.lawyerUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LawyerUserRepository extends JpaRepository<LawyerUser, Long> {
    Optional<LawyerUser> findByName(String name);
    Page<LawyerUser> findByCurrent(@Param("current") String current, Pageable pageable);

}
