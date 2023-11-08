package com.mily.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LawyerUserRepository extends JpaRepository<LawyerUser, Long> {
    List<LawyerUser> findById(long id);
}
