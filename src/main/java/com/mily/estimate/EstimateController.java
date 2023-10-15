package com.mily.estimate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EstimateController {
    private final EstimateService estimateService;

    @GetMapping("/estimate")
    public String showForm(EstimateForm estimateForm){
        return "estimate";
    }

    @PostMapping("/estimate")
    public String getEstimate(@Valid EstimateForm estimateForm){
        this.estimateService.saveEstimate(estimateForm.getCategory(), estimateForm.getCategoryItem());
        return String.format("redirect:/estimate");
    }
}
