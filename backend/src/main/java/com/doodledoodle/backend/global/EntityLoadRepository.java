package com.doodledoodle.backend.global;

import java.util.Optional;

public interface EntityLoadRepository<T, ID> {
    Optional<T> findById(final ID id);
}
