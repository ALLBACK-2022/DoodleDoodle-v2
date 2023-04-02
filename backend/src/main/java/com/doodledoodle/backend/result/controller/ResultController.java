package com.doodledoodle.backend.result.controller;

import com.doodledoodle.backend.result.dto.response.DrawResultResponseDto;
import com.doodledoodle.backend.result.dto.response.GameResultResponseDto;
import com.doodledoodle.backend.result.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/results")
public class ResultController {
    private final ResultService resultService;

    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameResultResponseDto> getResultByGameId(@PathVariable Long gameId) {
        return ResponseEntity.ok(resultService.getResultByGameId(gameId));
    }

    @GetMapping("/draw/{drawId}")
    public ResponseEntity<DrawResultResponseDto> getResultByDrawId(@PathVariable Long drawId) {
        return ResponseEntity.ok(resultService.getResultByDrawId(drawId));
    }
}
