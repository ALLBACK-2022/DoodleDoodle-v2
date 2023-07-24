package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResultResponse {
    private Long drawId;
    private Integer playerNo;
    private String imageUrl;
    private Double similarity;
}
