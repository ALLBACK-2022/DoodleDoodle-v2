package com.doodledoodle.backend.draw.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.doodledoodle.backend.draw.dto.request.DrawRequest;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.messagequeue.KafkaDrawProducer;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.support.database.DatabaseTest;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.mock.web.MockMultipartFile;

@DatabaseTest
@EmbeddedKafka(topics = {"doodledoodle.to.backend.result", "doodledoodle.to.ai.draw"})
@DisplayName("Draw 서비스의")
public class DrawServiceTest {

    @Autowired private DrawService drawService;
    @Autowired private GameRepository gameRepository;
    @Mock private AmazonS3Client amazonS3Client;
    @Mock private KafkaDrawProducer kafkaDrawProducer;


    @Test
    @DisplayName("사진이 저징이 되고 ai 모델로 요청을 하는가")
    void saveDraw() throws IOException {
        //given
        when(amazonS3Client.putObject(any())).thenReturn(new PutObjectResult());
        when(amazonS3Client.getUrl(any(),any())).thenReturn(new URL("http", "a", "b"));
        doNothing().when(kafkaDrawProducer).send(any());

        Integer playerNo = 1;
        MockMultipartFile file = new MockMultipartFile("images", "image.png", "MediaType.IMAGE_JPEG_VALUE", "Image".getBytes(
            StandardCharsets.UTF_8));
        Game gameEntity = gameRepository.save(new Game("skateboard",1));

        //when
        DrawResponse response = drawService.saveDraw(gameEntity.getId(), playerNo, file);

        //then
        assertThat(response.getDrawId()).isEqualTo(1L);
    }
}
