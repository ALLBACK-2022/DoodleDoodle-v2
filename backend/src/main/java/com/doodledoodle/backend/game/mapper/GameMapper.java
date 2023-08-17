package com.doodledoodle.backend.game.mapper;

import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.dto.response.GameWordResponse;
import com.doodledoodle.backend.game.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public Game toEntity(final GameRequest request) {
        return Game.builder()
                .playerNum(request.getPlayerNum())
                .build();
    }

    public GameWordResponse toResponse(final Game game) {
        return GameWordResponse.builder()
                .englishName(game.getDictionary().getEnglishName())
                .build();
    }
}
