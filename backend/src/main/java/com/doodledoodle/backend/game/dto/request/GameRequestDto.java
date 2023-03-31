package com.doodledoodle.backend.game.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameRequestDto {

  private Integer playerNum;

  @JsonCreator
  public GameRequestDto(@JsonProperty("user_num") Integer playerNum) {
    this.playerNum = playerNum;
  }
}
