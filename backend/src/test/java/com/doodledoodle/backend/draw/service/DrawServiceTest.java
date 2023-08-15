package com.doodledoodle.backend.draw.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.doodledoodle.backend.dictionary.repository.DictionaryRepository;
import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.messagequeue.KafkaDrawProducer;
import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.game.repository.GameRepositoryStandard;
import com.doodledoodle.backend.support.database.DatabaseTest;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.mock.web.MockMultipartFile;

import javax.persistence.EntityManager;

@DatabaseTest
@DisplayName("Draw 서비스의")
class DrawServiceTest {

    @Autowired private DrawService drawService;
    @Autowired private GameRepository gameRepository;
    @Autowired private DrawRepository drawRepository;
    @Autowired private DictionaryRepository dictionaryRepository;
    @Autowired private EntityManager entityManager;
    @MockBean private AmazonS3Client amazonS3Client;
    @MockBean private KafkaDrawProducer kafkaDrawProducer;


    @Test
    @DisplayName("사진이 저징이 되고 ai 모델로 요청을 하는가")
    void saveDraw() throws IOException {
        //given
        when(amazonS3Client.putObject(any())).thenReturn(new PutObjectResult());
        when(amazonS3Client.getUrl(any(),any())).thenReturn(new URL("http", "a", "b"));
        doNothing().when(kafkaDrawProducer).send(any());

        Integer playerNo = 1;
        MockMultipartFile file = new MockMultipartFile("images", "image.png", MediaType.IMAGE_JPEG_VALUE, "Image".getBytes(StandardCharsets.UTF_8));
        Game game = gameRepository.save(new Game(playerNo));
        gameRepository.findById(game.getId()).orElseThrow().updateDictionary(dictionaryRepository.findById(1L).orElseThrow());
        entityManager.flush();
        entityManager.clear();

        //when
        final UUID gameId = game.getId();
        DrawResponse response = drawService.saveDraw(gameId, playerNo, file);

        //then
        final Draw draw = drawRepository.findById(response.getDrawId()).orElseThrow();

        assertThat(draw.getGame().getId()).isEqualTo(gameId);
        assertThat(draw.getPlayerNo()).isEqualTo(playerNo);
    }
}
