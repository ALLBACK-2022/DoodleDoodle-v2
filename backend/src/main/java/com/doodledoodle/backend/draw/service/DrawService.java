package com.doodledoodle.backend.draw.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.doodledoodle.backend.draw.dto.kafka.DrawKafkaRequest;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.mapper.DrawMapper;
import com.doodledoodle.backend.draw.messagequeue.KafkaDrawProducer;
import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.global.storage.S3StorageProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DrawService implements EntityLoader<Draw, Long> {
    private static final String MAIN_DIRECTORY = "drawimage/";
    private static final String FILE_EXTENSION_SPLITTER = ".";
    DrawRepository drawRepository;
    EntityLoader<Game, Long> gameService;
    DrawMapper drawMapper;
    S3StorageProperties s3StorageProperties;
    KafkaDrawProducer kafkaDrawProducer;
    AmazonS3Client amazonS3Client;

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
        String fileName = getFileName(file, draw);
        uploadToS3(file, fileName);
        draw.updateImgUrl(getImageUrl(fileName));
    }

    private void uploadToS3(final MultipartFile file, final String fileName) throws IOException {
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(fileName);
        data.setContentLength(file.getSize());

        amazonS3Client.putObject(
                new PutObjectRequest(s3StorageProperties.getBucket(), fileName, file.getInputStream(), data)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private String getImageUrl(final String fileName) {
        return amazonS3Client.getUrl(s3StorageProperties.getBucket(), fileName).toString();
    }

    private void requestKafkaAiResult(final Long drawId, final String randomWord, final MultipartFile file) {
        kafkaDrawProducer.send(new DrawKafkaRequest(drawId, randomWord, file));
    }

    private String getFileName(final MultipartFile file, final Draw draw) {
        return String.join("",  MAIN_DIRECTORY, draw.getId().toString(), FILE_EXTENSION_SPLITTER, StringUtils.getFilenameExtension(file.getOriginalFilename()));
    }

    @Override
    public Draw loadEntity(final Long id) {
        return drawRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
