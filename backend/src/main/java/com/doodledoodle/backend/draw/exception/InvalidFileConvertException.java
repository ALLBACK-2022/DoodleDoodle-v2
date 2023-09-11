package com.doodledoodle.backend.draw.exception;

public class InvalidFileConvertException extends IllegalArgumentException {
    private static final String MESSAGE = "잘못된 파일 변환입니다.";

    public InvalidFileConvertException() {
        super(MESSAGE);
    }
}
