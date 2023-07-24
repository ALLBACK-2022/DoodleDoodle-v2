package com.doodledoodle.backend.draw.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.doodledoodle.backend.draw.dto.kafka.DrawKafkaRequest;
import com.doodledoodle.backend.draw.dto.request.DrawRequest;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.mapper.DrawMapper;
import com.doodledoodle.backend.draw.messagequeue.KafkaDrawProducer;
import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.service.GameService;
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
    static String mainDirectory = "drawimage/";
    static String dot = ".";
    DrawRepository drawRepository;
    GameService gameService;
    DrawMapper drawMapper;
    S3StorageProperties s3StorageProperties;
    KafkaDrawProducer kafkaDrawProducer;
    AmazonS3Client amazonS3Client;

    @Transactional
    public DrawResponse saveDraw(final DrawRequest request) throws IOException {
        Game game = gameService.loadEntity(request.getGameId());
        Draw draw = drawRepository.save(drawMapper.toEntity(request, game));

        uploadToS3AndUpdateDraw(request, draw);
        requestKafkaAiResult(draw.getId(), game.getEnglishName(), request.getFile());

        drawRepository.save(draw);
        return drawMapper.toResponse(draw);
    }

    private void uploadToS3AndUpdateDraw(final DrawRequest request, final Draw draw) throws IOException {
        String fileName = getFileName(request, draw);
        uploadToS3(request, fileName);
        draw.updateImgUrl(getImageUrl(fileName));
    }

    private void uploadToS3(final DrawRequest request, final String fileName) throws IOException {
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(fileName);
        data.setContentLength(request.getFile().getSize());

        amazonS3Client.putObject(
                new PutObjectRequest(s3StorageProperties.getBucket(), fileName, request.getFile().getInputStream(), data)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private String getImageUrl(final String fileName) {
        return amazonS3Client.getUrl(s3StorageProperties.getBucket(), fileName).toString();
    }

    private void requestKafkaAiResult(final Long drawId, final String randomWord, final MultipartFile file) {
        kafkaDrawProducer.send(new DrawKafkaRequest(drawId, randomWord, file));
    }

    private String getFileName(final DrawRequest request, final Draw draw) {
        return String.join("",  mainDirectory, draw.getId().toString(), dot, StringUtils.getFilenameExtension(request.getFile().getOriginalFilename()));
    }

    @Override
    public Draw loadEntity(final Long id) {
        return drawRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
