package com.doodledoodle.backend.draw.mapper;

import com.doodledoodle.backend.draw.dto.kafka.DrawKafkaRequest;
import com.doodledoodle.backend.draw.dto.request.DrawRequest;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class DrawMapper {

  public Draw toEntity(final DrawRequest request, Game game){
    return Draw.builder()
        .game(game)
        .drawNo(request.getDrawNo())
        .build();
  }

  public DrawResponse toResponse(final Draw draw){
    return DrawResponse.builder()
        .drawId(draw.getId())
        .build();
  }

  public DrawKafkaRequest toDto(final Draw draw, final Game game){
    return DrawKafkaRequest.builder()
        .drawId(draw.getId())
        .randomWord(game.getRandomWord())
        .build();
  }
}
