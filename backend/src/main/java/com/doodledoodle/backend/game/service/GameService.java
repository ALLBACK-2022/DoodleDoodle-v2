package com.doodledoodle.backend.game.service;

import com.doodledoodle.backend.game.dto.request.GameRequestDto;
import com.doodledoodle.backend.game.dto.response.GameResponseDto;
import com.doodledoodle.backend.game.mapper.GameMapper;
import com.doodledoodle.backend.game.repository.GameRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GameService {

  private final GameRepository gameRepository;
  private final GameMapper gameMapper;

  public GameResponseDto createGame(GameRequestDto requestDto) {
    return gameMapper.mapEntityToGameResponse(gameRepository.save(gameMapper.toEntity(requestDto)));
  }
}
