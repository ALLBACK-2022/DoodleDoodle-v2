package com.doodledoodle.backend.game.exception;

import com.doodledoodle.backend.global.exception.ErrorResponse;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GameException {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> validationException(ValidationException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
  }
}
