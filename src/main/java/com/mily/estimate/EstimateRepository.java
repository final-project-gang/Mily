package com.mily.estimate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    List<Estimate> findByCategoryAndArea(String category, String area);
    List<Estimate> findByCategory(String category);
    List<Estimate> findByArea(String area);
}
