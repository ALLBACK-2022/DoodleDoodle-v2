package com.doodledoodle.backend.game.mapper;

import com.doodledoodle.backend.game.dto.request.GameRequestDto;
import com.doodledoodle.backend.game.dto.response.GameResponseDto;
import com.doodledoodle.backend.game.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

  public Game toEntity(GameRequestDto requestDto) {
    return Game.builder()
        .playerNum(requestDto.getPlayerNum())
        .build();
  }

  public GameResponseDto mapEntityToGameResponse(Game entity) {
    return GameResponseDto.builder()
        .id(entity.getId())
        .build();
  }
}
