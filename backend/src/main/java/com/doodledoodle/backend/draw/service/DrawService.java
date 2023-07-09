package com.doodledoodle.backend.draw.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
import com.doodledoodle.backend.global.exception.FileStorageException;
import com.doodledoodle.backend.global.storage.S3StorageProperties;
import java.io.IOException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DrawService implements EntityLoader<Draw, Long> {

  DrawRepository drawRepository;
  GameService gameService;
  DrawMapper drawMapper;
  S3StorageProperties s3StorageProperties;
  KafkaDrawProducer kafkaDrawProducer;
  AmazonS3Client amazonS3Client;


  public DrawResponse saveDraw(final DrawRequest request) {
    Game game = gameService.loadEntity(request.getGameId());
    Draw draw = drawRepository.save(drawMapper.toEntity(request, game));
    String[] splitFilename = Objects.requireNonNull(request.getFileName().getOriginalFilename())
        .split("\\.");
    String fileType = splitFilename[splitFilename.length - 1];
    String fileName = "drawimage/" + draw.getId().toString() + "." + fileType;
    kafkaDrawProducer.send(drawMapper.toDto(draw,game,request));
    ObjectMetadata data = new ObjectMetadata();
    data.setContentType(fileName);
    data.setContentLength(request.getFileName().getSize());
    try {
      amazonS3Client.putObject(
          new PutObjectRequest(
              s3StorageProperties.getBucket(),
              fileName,
              request.getFileName().getInputStream(),
              data)
              .withCannedAcl(CannedAccessControlList.PublicRead));
      String downloadUri = amazonS3Client.getUrl(s3StorageProperties.getBucket(), fileName)
          .toString();
      draw.update(downloadUri);
      drawRepository.save(draw);
    } catch (IOException e) {
      throw new FileStorageException();
    }
    return drawMapper.toResponse(draw);
  }

  @Override
  public Draw loadEntity(final Long id) {
    return drawRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }
}
