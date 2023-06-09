package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.service.DrawService;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.service.GameService;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.entity.DictionaryMap;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.entity.SimilarityMap;
import com.doodledoodle.backend.result.mapper.ResultMapper;
import com.doodledoodle.backend.result.repository.ResultRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ResultService implements EntityLoader<Result, Long> {
    ResultRepository resultRepository;
    ResultMapper resultMapper;
    DrawService drawService;
    DictionaryService dictionaryService;
    GameService gameService;

    public void saveResults(ResultKafkaResponse resultKafkaResponse) {
        Draw draw = drawService.loadEntity(resultKafkaResponse.getDrawId());
        SimilarityMap similarityMap = new SimilarityMap(resultKafkaResponse.getResult());

        DictionaryMap dictionaries = dictionaryService.getEntityListByEngName(similarityMap.getKeySet());
        List<Result> results = resultMapper.toEntityList(similarityMap, draw, draw.getGame(), dictionaries);

        resultRepository.saveAll(results);
    }

    public DrawResultResponse getResultByDrawId(Long drawId) {
        List<Result> results = resultRepository.findByDrawIdOrderBySimilarityDesc(drawId);
        Draw draw = drawService.loadEntity(drawId);

        return resultMapper.toDrawResultResponse(draw, results);
    }

    public GameResultResponse getResultByGameId(Long gameId) {
        List<Result> results = resultRepository.findByGameIdOrderBySimilarityDesc(gameId);
        Game game = gameService.loadEntity(gameId);

        return resultMapper.toGameResultResponse(game, results);
    }

    @Override
    public Result loadEntity(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
