package com.doodledoodle.backend.draw.service;

import com.doodledoodle.backend.draw.dto.kafka.DrawKafkaRequest;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.mapper.DrawMapper;
import com.doodledoodle.backend.draw.messagequeue.KafkaDrawProducer;
import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.utils.ImageS3Component;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DrawService implements EntityLoader<Draw, Long> {
    DrawRepository drawRepository;
    EntityLoader<Game, Long> gameService;
    DrawMapper drawMapper;
    KafkaDrawProducer kafkaDrawProducer;
    ImageS3Component imageS3Component;

    @Transactional
    public DrawResponse saveDraw(final Long gameId, final Integer playerNo, final MultipartFile file) throws IOException {
        Game game = gameService.loadEntity(gameId);
        Draw draw = drawRepository.save(drawMapper.toEntity(playerNo, game));

        uploadToS3AndUpdateDraw(file, draw);
        requestKafkaAiResult(draw.getId(), game.getEnglishName(), file);

        drawRepository.save(draw);
        return drawMapper.toResponse(draw);
    }

    private void uploadToS3AndUpdateDraw(final MultipartFile file, final Draw draw) throws IOException {
        String imageUrl = imageS3Component.uploadAndGetUrl(file, draw.getId());
        draw.updateImgUrl(imageUrl);
    }

    private void requestKafkaAiResult(final Long drawId, final String randomWord, final MultipartFile file) {
        kafkaDrawProducer.send(new DrawKafkaRequest(drawId, randomWord, file));
    }

    @Override
    public Draw loadEntity(final Long id) {
        return drawRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
