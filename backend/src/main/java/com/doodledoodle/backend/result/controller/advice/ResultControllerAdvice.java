package com.doodledoodle.backend.result.controller.advice;

import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.exception.DrawResultNotFoundException;
import com.doodledoodle.backend.result.exception.GameResultNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResultControllerAdvice {

    @ExceptionHandler(DrawResultNotFoundException.class)
    public ResponseEntity<DrawResultResponse> drawResultNotFoundException(final DrawResultNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(DrawResultResponse.builder().build());
    }

    @ExceptionHandler(GameResultNotFoundException.class)
    public ResponseEntity<GameResultResponse> gameResultNotFoundException(final GameResultNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(GameResultResponse.builder().build());
    }
}
