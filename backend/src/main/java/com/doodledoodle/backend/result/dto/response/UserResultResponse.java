package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UserResultResponse {
    private UUID drawId;
    private Integer playerNo;
    private String imageUrl;
    private Double similarity;
}
