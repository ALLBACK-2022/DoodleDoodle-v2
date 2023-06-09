package com.doodledoodle.backend.game.controller;

import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.service.GameService;
import com.doodledoodle.backend.global.IdResponse;
import javax.validation.Valid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameController {

  GameService gameService;

  @PostMapping
  public ResponseEntity<IdResponse<Long>> createGame(
      @Valid @RequestBody GameRequest requestDto) {
    return ResponseEntity.ok(gameService.createGame(requestDto));
  }
}
