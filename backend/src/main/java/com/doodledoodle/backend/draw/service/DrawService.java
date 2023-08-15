package com.doodledoodle.backend.draw.service;

import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.global.EntityLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface DrawService extends EntityLoader<Draw, UUID> {
    DrawResponse saveDraw(final UUID gameId, final Integer playerNo, final MultipartFile file) throws IOException;
}
