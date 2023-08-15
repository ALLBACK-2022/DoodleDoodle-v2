package com.doodledoodle.backend.game.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameWordRequest {
    @NotNull
    private UUID id;
    @NotNull
    private String koreanName;
}
