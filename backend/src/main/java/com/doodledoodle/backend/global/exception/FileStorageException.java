package com.doodledoodle.backend.global.exception;

public class FileStorageException extends IllegalStateException {
    private static final String MESSAGE = "파일 스토리지 접근에 오류가 발생했습니다.";

    public FileStorageException() {
        super(MESSAGE);
    }
}

