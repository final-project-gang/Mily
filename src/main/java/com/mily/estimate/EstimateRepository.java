package com.mily.estimate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    List<Estimate> findByCreateDateGreaterThanEqualAndArea(LocalDateTime createDate, String area);
    List<Estimate> findByCreateDateGreaterThanEqualAndAreaAndCategory(LocalDateTime createDate, String area, String category);
    List<Estimate> findAll();
    void deleteByCreateDateLessThan(LocalDateTime createDate);
}