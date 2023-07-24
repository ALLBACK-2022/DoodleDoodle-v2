package com.doodledoodle.backend.draw.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DrawResponse {
    private Long drawId;
}
