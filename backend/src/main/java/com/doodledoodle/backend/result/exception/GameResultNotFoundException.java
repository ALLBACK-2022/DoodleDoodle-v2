package com.doodledoodle.backend.result.exception;

public class GameResultNotFoundException extends IllegalStateException {
    private static final String MESSAGE = "게임 결과를 찾을 수 없습니다.";

    public GameResultNotFoundException() {
        super(MESSAGE);
    }
}
