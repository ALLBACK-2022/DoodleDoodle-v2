package com.doodledoodle.backend.game.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameWordRequest {
    @NotNull
    private Long id;
    @NotNull
    private String koreanName;
}
