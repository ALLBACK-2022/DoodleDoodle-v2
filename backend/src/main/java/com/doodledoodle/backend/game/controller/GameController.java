package com.doodledoodle.backend.game.controller;

import com.doodledoodle.backend.game.dto.request.GameRequestDto;
import com.doodledoodle.backend.game.dto.response.GameResponseDto;
import com.doodledoodle.backend.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

  private final GameService gameService;

  @PostMapping
  public ResponseEntity<GameResponseDto> createGame(
      @RequestBody GameRequestDto requestDto) {
    return ResponseEntity.ok(gameService.createGame(requestDto));
  }

}
