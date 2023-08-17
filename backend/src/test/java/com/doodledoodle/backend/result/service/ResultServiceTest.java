package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.dictionary.repository.DictionaryRepository;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.repository.ResultRepository;
import com.doodledoodle.backend.support.database.DatabaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DatabaseTest
@DisplayName("Result 서비스의")
class ResultServiceTest {
    @Autowired private ResultService resultService;
    @Autowired private ResultRepository resultRepository;
    @Autowired private DrawRepository drawRepository;
    @Autowired private GameRepository gameRepository;
    @Autowired private DictionaryRepository dictionaryRepository;

    @Test
    @DisplayName("결과 저장이 수행되는가")
    void saveResults() {
        //given
        Game game = gameRepository.save(new Game(1));
        game.updateDictionary(dictionaryRepository.findById(1L).orElseThrow());
        Draw draw = drawRepository.save(new Draw("imageUrl", game, 1));
        Map<String, Double> resultMap = Map.of(game.getDictionary().getEnglishName(), 0.77);
        Map<String, Double> topFiveMap = Map.of(
                "donut", 8.97,
                "snowman", 8.44,
                "paint_can", 6.34,
                "chair", 5.21,
                "book", 4.89
        );

        //when
        resultService.saveResults(new ResultKafkaResponse(draw.getId(), resultMap, topFiveMap));

        //then
        List<Result> results = resultRepository.findByDrawId(draw.getId());
        Result result = resultRepository.findById(results.get(0).getId()).orElseThrow();

        assertThat(result.getGame().getId()).isEqualTo(game.getId());
    }

    @Test
    @Transactional
    @DisplayName("Draw PK로 조회가 수행되는가")
    void getResultByDrawId() {
        //given
        long dictionaryId = 1L;
        final UUID gameId = gameRepository.save(new Game(1)).getId();
        Game game = gameRepository.findById(gameId).orElseThrow();
        game.updateDictionary(dictionaryRepository.findById(dictionaryId).orElseThrow());
        UUID drawId = drawRepository.save(new Draw("imageUrl", game, 1)).getId();
        Map<String, Double> resultMap = Map.of(game.getDictionary().getEnglishName(), 0.77);
        Map<String, Double> topFiveMap = Map.of(
                "donut", 0.77,
                "snowman", 8.44,
                "paint_can", 6.34,
                "chair", 5.21,
                "book", 4.89
        );
        resultService.saveResults(new ResultKafkaResponse(drawId, resultMap, topFiveMap));

        //when
        DrawResultResponse response = resultService.getResultByDrawId(drawId);

        //then
        assertThat(response.getRandomWord().getEnglishName()).isEqualTo(gameRepository.findById(gameId).orElseThrow().getDictionary().getEnglishName());
    }

    @Test
    @Transactional
    @DisplayName("Game PK로 조회가 수행되는가")
    void getResultByGameId() {
        //given
        long dictionaryId = 1L;
        UUID gameId = gameRepository.save(new Game(1)).getId();
        Game game = gameRepository.findById(gameId).orElseThrow();
        game.updateDictionary(dictionaryRepository.findById(dictionaryId).orElseThrow());
        UUID drawId = drawRepository.save(new Draw("imageUrl", game, 1)).getId();
        Map<String, Double> resultMap = Map.of(game.getDictionary().getEnglishName(), 0.77);
        Map<String, Double> topFiveMap = Map.of(
                "donut", 0.77,
                "snowman", 8.44,
                "paint_can", 6.34,
                "chair", 5.21,
                "book", 4.89
        );
        resultService.saveResults(new ResultKafkaResponse(drawId, resultMap, topFiveMap));

        //when
        GameResultResponse response = resultService.getResultByGameId(gameId);

        //then
        assertThat(response.getRandomWord()).isEqualTo(gameRepository.findById(gameId).orElseThrow().getDictionary().getKoreanName());
    }
}
