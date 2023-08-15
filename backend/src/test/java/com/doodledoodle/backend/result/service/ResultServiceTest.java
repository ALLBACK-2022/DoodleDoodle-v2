package com.doodledoodle.backend.result.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.repository.DictionaryRepositoryStandard;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.repository.DrawRepositoryStandard;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepositoryStandard;
import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.repository.ResultRepositoryStandard;
import com.doodledoodle.backend.support.database.DatabaseTest;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.context.EmbeddedKafka;

@DatabaseTest
@EmbeddedKafka(topics = {"doodledoodle.to.backend.result", "doodledoodle.to.ai.draw"})
@DisplayName("Result 서비스의")
class ResultServiceTest {
    @Autowired private ResultServiceImpl resultService;
    @Autowired private ResultRepositoryStandard resultRepository;
    @Autowired private DrawRepositoryStandard drawRepository;
    @Autowired private GameRepositoryStandard gameRepository;
    @Autowired private DictionaryRepositoryStandard dictionaryRepository;

    @Test
    @DisplayName("결과 저장이 수행되는가")
    void saveResults() {
        //given
        Game game = gameRepository.save(new Game(1));
        Draw draw = drawRepository.save(new Draw(" ", game,1));
        Map<String, Double> resultMap = Map.of(game.getDictionary().getEnglishName(), 0.77);
        Map<String, Double> topFiveMap = Map.of(
            "donut", 8.97,
            "snowman", 8.44,
            "paint_can", 6.34,
            "chair", 5.21,
            "book", 4.89
        );

        //when
        resultService.saveResults(new ResultKafkaResponse(draw.getId(),resultMap,topFiveMap));

        //then
        List<Result> results= resultRepository.findByDrawId(draw.getId());
        Result resultEntity = resultRepository.findById(results.get(0).getId()).orElseThrow();

        assertThat(resultEntity.getGame().getId()).isEqualTo(game.getId());
    }



    @Nested
    @DisplayName("결과 조회 로직 중")
    class selectResult {
        private Game gameEntity;
        private Draw drawEntity;

        @BeforeEach
        void init() {
            gameEntity = gameRepository.save(new Game(1));
            drawEntity = drawRepository.save(new Draw(" ", gameEntity,1));
            Map<String, Double> resultMap = Map.of(gameEntity.getDictionary().getEnglishName(), 0.77);
            Map<String, Double> topFiveMap = Map.of(
                    "donut", 0.77,
                    "snowman", 8.44,
                    "paint_can", 6.34,
                    "chair", 5.21,
                    "book", 4.89
            );
            resultService.saveResults(new ResultKafkaResponse(drawEntity.getId(), resultMap, topFiveMap));
        }

        @Test
        @DisplayName("Draw PK로 조회가 수행되는가")
        void getResultByDrawId() {
            //given

            //when
            DrawResultResponse response = resultService.getResultByDrawId(drawEntity.getId());

            //then
            List<Result> results= resultRepository.findByDrawId(drawEntity.getId());

//            assertThat(response.getRandomWord().getEnglishName()).isEqualTo(dictionaryEntity.getEnglishName());
        }

        @Test
        @DisplayName("Game PK로 조회가 수행되는가")
        void getResultByGameId() {
            //given

            //when
            GameResultResponse response = resultService.getResultByGameId(gameEntity.getId());

            //then
            assertThat(response.getRandomWord()).isEqualTo(gameEntity.getDictionary().getEnglishName());
        }
    }
}
