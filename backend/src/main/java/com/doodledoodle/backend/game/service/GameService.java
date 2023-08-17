package com.doodledoodle.backend.game.service;

import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.dto.request.GameWordRequest;
import com.doodledoodle.backend.game.dto.response.GameWordResponse;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.dto.IdResponse;

import java.util.UUID;

public interface GameService extends EntityLoader<Game, UUID> {
    IdResponse<UUID> createGame(final GameRequest requestDto);

    GameWordResponse saveWord(final GameWordRequest gameWordRequest);
}
