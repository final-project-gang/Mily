package com.mily.user.lawyerUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LawyerUserRepository extends JpaRepository<LawyerUser, Long> {
}
