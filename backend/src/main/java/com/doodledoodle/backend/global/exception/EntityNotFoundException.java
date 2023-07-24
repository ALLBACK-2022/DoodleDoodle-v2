package com.doodledoodle.backend.global.exception;

public class EntityNotFoundException extends IllegalStateException {
    private static final String MESSAGE = "엔티티가 존재하지 않습니다.";

    public EntityNotFoundException() {
        super(MESSAGE);
    }
}
