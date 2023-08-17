package com.doodledoodle.backend.global;

public interface EntityLoader<T, ID> {
    T loadEntity(final ID id);
}
