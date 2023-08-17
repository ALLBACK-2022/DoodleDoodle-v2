package com.doodledoodle.backend.result.exception;

public class DrawResultNotFoundException extends IllegalStateException {
    private static final String MESSAGE = "그림 결과를 찾을 수 없습니다.";

    public DrawResultNotFoundException() {
        super(MESSAGE);
    }
}
