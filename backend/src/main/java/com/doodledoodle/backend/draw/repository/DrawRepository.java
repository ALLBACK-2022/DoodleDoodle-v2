package com.doodledoodle.backend.draw.repository;

import com.doodledoodle.backend.draw.entity.Draw;

import java.util.Optional;

public interface DrawRepository {
    Optional<Draw> findById(Long id);
}
