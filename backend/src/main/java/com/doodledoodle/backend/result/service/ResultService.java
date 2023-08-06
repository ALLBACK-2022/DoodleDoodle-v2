package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.service.DrawService;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.service.GameService;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.result.dto.collection.DictionaryMap;
import com.doodledoodle.backend.result.dto.collection.SimilarityMap;
import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.mapper.ResultMapper;
import com.doodledoodle.backend.result.repository.ResultRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class ResultService implements EntityLoader<Result, Long> {
    ResultRepository resultRepository;
    ResultMapper resultMapper;
    DrawService drawService;
    DictionaryService dictionaryService;
    GameService gameService;

    public void saveResults(final ResultKafkaResponse resultKafkaResponse) {
        Draw draw = drawService.loadEntity(resultKafkaResponse.getDrawId());
        SimilarityMap similarityMap = new SimilarityMap(resultKafkaResponse.getResult());
        SimilarityMap topFiveSimilarityMap = new SimilarityMap(resultKafkaResponse.getTopFive());
        saveAllBySimilarityMap(draw, similarityMap);
        saveAllBySimilarityMap(draw, topFiveSimilarityMap);
    }

    private void saveAllBySimilarityMap(final Draw draw, final SimilarityMap similarityMap) {
        DictionaryMap dictionaries = dictionaryService.getDictionaryMapByEnglishNames(similarityMap.getKeySet());
        List<Result> results = resultMapper.toEntityList(similarityMap, draw, draw.getGame(), dictionaries);
        resultRepository.saveAll(results);
    }

    public DrawResultResponse getResultByDrawId(final Long drawId) {
        List<Result> results = resultRepository.findByDrawIdOrderBySimilarityDesc(drawId);
        if (results.isEmpty()) {
            return resultMapper.toEmptyDrawResponse();
        }
        Draw draw = drawService.loadEntity(drawId);
        return resultMapper.toDrawResultResponse(draw, results);
    }

    public GameResultResponse getResultByGameId(final Long gameId) {
        List<Result> results = resultRepository.findByGameIdOrderBySimilarityDesc(gameId);
        Game game = gameService.loadEntity(gameId);

        return resultMapper.toGameResultResponse(game, results);
    }

    @Override
    public Result loadEntity(final Long id) {
        return resultRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
