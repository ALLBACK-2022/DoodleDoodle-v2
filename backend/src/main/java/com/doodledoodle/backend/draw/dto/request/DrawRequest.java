package com.doodledoodle.backend.draw.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrawRequest {

  private Long gameId;
  private Integer drawNo;
}
