package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResultResponseDto {
    private Long drawId;
    private Long drawNo;
    private String imgUrl;
    private Float Similarity;
}
