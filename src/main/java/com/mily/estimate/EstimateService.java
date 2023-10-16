package com.mily.estimate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstimateService {
    private final EstimateRepository estimateRepository;

    public void saveEstimate(String category, String categoryItem) {
        Estimate estimate = new Estimate();
        estimate.setCategory(category);
        estimate.setCategoryItem(categoryItem);
        this.estimateRepository.save(estimate);
    }
}
