package com.doodledoodle.backend.game.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameRequest {
    @Range(min = 0, max = 6)
    private Integer playerNum;
}
