package com.mily.estimate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstimateForm {
    @NotEmpty(message = "카테고리 선택은 필수입니다.")
    private String category;

    @NotEmpty(message = "상세 항목은 필수입니다.")
    private String categoryItem;
}
