package com.doodledoodle.backend.draw.service;

import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.global.EntityLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrawService implements EntityLoader<Draw, Long> {
    private final DrawRepository drawRepository;

    @Override
    public Draw loadEntity(Long id) {
        return drawRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
