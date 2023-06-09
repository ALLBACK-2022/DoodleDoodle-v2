package com.doodledoodle.backend.game.service;

import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.mapper.GameMapper;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.global.IdResponse;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.global.EntityLoader;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameService implements EntityLoader<Game, Long> {
  GameRepository gameRepository;
  GameMapper gameMapper;

  public IdResponse<Long> createGame(GameRequest requestDto) {
    Game game = gameRepository.save(gameMapper.toEntity(requestDto));
    return new IdResponse<>(game.getId());
  }

  @Override
  public Game loadEntity(Long id) {
    return gameRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
  }
}
