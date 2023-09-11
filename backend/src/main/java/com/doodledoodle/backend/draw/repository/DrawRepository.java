package com.doodledoodle.backend.draw.repository;

import com.doodledoodle.backend.draw.entity.Draw;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DrawRepository {
    DrawRepositoryStandard drawRepositoryStandard;

    public Optional<Draw> findById(final UUID id) {
        return drawRepositoryStandard.findById(id);
    }

    public Draw save(final Draw draw) {
        return drawRepositoryStandard.save(draw);
    }
}
