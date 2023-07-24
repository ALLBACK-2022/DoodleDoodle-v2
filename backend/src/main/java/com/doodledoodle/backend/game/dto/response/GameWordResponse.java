package com.doodledoodle.backend.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GameWordResponse {
    private String englishName;
}
