package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.result.dto.collection.EnglishNameMap;
import com.doodledoodle.backend.result.dto.collection.DictionaryMap;
import com.doodledoodle.backend.result.dto.collection.SimilarityMap;
import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.exception.DrawResultNotFoundException;
import com.doodledoodle.backend.result.exception.GameResultNotFoundException;
import com.doodledoodle.backend.result.mapper.ResultMapper;
import com.doodledoodle.backend.result.repository.ResultRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ResultServiceImpl implements ResultService {
    ResultRepository resultRepository;
    ResultMapper resultMapper;
    DictionaryService dictionaryService;
    EntityLoader<Draw, UUID> drawService;
    EntityLoader<Game, UUID> gameService;

    @Override
    public void saveResults(final ResultKafkaResponse resultKafkaResponse) {
        final Draw draw = drawService.loadEntity(resultKafkaResponse.getDrawId());

        final SimilarityMap randomWordSimilarityMap = new SimilarityMap(resultKafkaResponse.getResult());
        final SimilarityMap topFiveSimilarityMap = new SimilarityMap(resultKafkaResponse.getTopFive());

        saveAllResult(randomWordSimilarityMap, draw);
        saveAllResult(topFiveSimilarityMap, draw);
    }

    private void saveAllResult(final SimilarityMap similarityMap, final Draw draw) {
        final EnglishNameMap englishNameMap = dictionaryService.getDictionaryMapByEnglishNames(similarityMap);
        final DictionaryMap dictionaryMap = new DictionaryMap(similarityMap, englishNameMap);
        saveAllByDictionaryMap(draw, dictionaryMap);
    }

    private void saveAllByDictionaryMap(final Draw draw, final DictionaryMap similarityMap) {
        final List<Result> results = resultMapper.toEntityList(similarityMap, draw);
        resultRepository.saveAll(results);
    }

    @Override
    @Transactional(readOnly = true)
    public DrawResultResponse getResultByDrawId(final UUID drawId) {
        final List<Result> results = resultRepository.findByDrawId(drawId);
        if (results.isEmpty()) {
            throw new DrawResultNotFoundException();
        }
        final Draw draw = drawService.loadEntity(drawId);
        return resultMapper.toDrawResultResponse(draw, results);
    }

    @Override
    @Transactional(readOnly = true)
    public GameResultResponse getResultByGameId(final UUID gameId) {
        final List<Result> results = resultRepository.findByGameId(gameId);
        final Game game = gameService.loadEntity(gameId);

        if (noLastPlayerResult(results, game)) {
            throw new GameResultNotFoundException();
        }
        return resultMapper.toGameResultResponse(game, results);
    }

    private boolean noLastPlayerResult(final List<Result> results, final Game game) {
        return results.stream()
                .map(Result::getDraw)
                .noneMatch(d -> d.getPlayerNo().intValue() == game.getPlayerNum().intValue());
    }

    @Override
    public Result loadEntity(final UUID id) {
        return resultRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
