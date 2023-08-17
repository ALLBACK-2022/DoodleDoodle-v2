package com.doodledoodle.backend.draw.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class DrawResponse {
    private UUID drawId;
}
