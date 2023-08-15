package com.doodledoodle.backend.draw.repository;

import com.doodledoodle.backend.draw.entity.Draw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface JpaDrawRepository extends JpaRepository<Draw, UUID>, DrawRepositoryStandard {
    @Query("SELECT d FROM Draw d LEFT JOIN FETCH d.game WHERE d.id = :id")
    Optional<Draw> findById(final UUID id);
}
