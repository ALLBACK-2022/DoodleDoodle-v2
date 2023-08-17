package com.doodledoodle.backend.global;

public interface EntityRepository<T, ID> extends EntityLoadRepository<T, ID> {
    T save(final T entity);
}
