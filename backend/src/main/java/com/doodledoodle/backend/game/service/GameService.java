package com.doodledoodle.backend.game.service;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.repository.DictionaryRepository;
import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.dto.request.GameWordRequest;
import com.doodledoodle.backend.game.dto.response.GameWordResponse;
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
  DictionaryRepository dictionaryRepository;
  GameMapper gameMapper;

  public IdResponse<Long> createGame(final GameRequest requestDto) {
    Game game = gameRepository.save(gameMapper.toEntity(requestDto));
    return new IdResponse<>(game.getId());
  }

  public GameWordResponse setWord(final GameWordRequest gameWordRequest) {
    Game game = gameRepository.findById(gameWordRequest.getId()).orElseThrow(
        EntityNotFoundException::new);
    Dictionary dictionary = dictionaryRepository.findByKoreanName(gameWordRequest.getName())
        .orElseThrow(EntityNotFoundException::new);
    game.update(game.getId(),dictionary.getEnglishName());
    gameRepository.save(game);
    return gameMapper.toResponse(game);
  }

  @Override
  public Game loadEntity(final Long id) {
    return gameRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }
}
