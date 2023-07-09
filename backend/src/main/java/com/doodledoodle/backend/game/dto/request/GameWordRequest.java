package com.doodledoodle.backend.game.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameWordRequest {

  @NotNull
  private Long id;
  @NotNull
  private String name;
}
