package com.doodledoodle.backend.result.controller;

import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.service.ResultService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/results")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ResultController {
    ResultService resultService;

    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameResultResponse> getResultByGameId(@PathVariable Long gameId) {
        return ResponseEntity.ok(resultService.getResultByGameId(gameId));
    }

    @GetMapping("/draw/{drawId}")
    public ResponseEntity<DrawResultResponse> getResultByDrawId(@PathVariable Long drawId) {
        if (resultService.getResultByDrawId(drawId).getTopFive().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultService.getResultByDrawId(drawId));
    }
}
