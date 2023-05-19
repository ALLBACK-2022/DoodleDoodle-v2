package com.doodledoodle.backend.global;

public interface EntityLoader<T> {
    T loadEntity(Long id);
}
