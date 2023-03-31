package com.doodledoodle.backend.game.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameRequestDto {

  @Max(value = 6, message = "인원수의 범위는 1 ~ 6이여야 합니다.")
  @Min(value = 1, message = "인원수의 범위는 1 ~ 6이여야 합니다.")
  private Integer playerNum;

  @JsonCreator
  public GameRequestDto(@JsonProperty("user_num") Integer playerNum) {
    this.playerNum = playerNum;
  }
}
