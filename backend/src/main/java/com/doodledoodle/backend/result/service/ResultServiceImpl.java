package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.entity.collection.DictionarySimilarity;
import com.doodledoodle.backend.result.entity.collection.DrawSimilarity;
import com.doodledoodle.backend.result.entity.collection.EnglishNames;
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
    private static final int RESULT_PER_PLAYER_SIZE = 6;
    ResultRepository resultRepository;
    ResultMapper resultMapper;
    DictionaryService dictionaryService;
    EntityLoader<Draw, UUID> drawService;
    EntityLoader<Game, UUID> gameService;

    @Override
    public void saveResults(final ResultKafkaResponse resultKafkaResponse) {
        final Draw draw = drawService.loadEntity(resultKafkaResponse.getDrawId());

        saveAllResult(new DrawSimilarity(resultKafkaResponse.getResult()), draw);
        saveAllResult(new DrawSimilarity(resultKafkaResponse.getTopFive()), draw);
    }

    private void saveAllResult(final DrawSimilarity drawSimilarity, final Draw draw) {
        final EnglishNames englishNames = dictionaryService.getDictionaryMapByEnglishNames(drawSimilarity);
        final DictionarySimilarity dictionarySimilarity = new DictionarySimilarity(drawSimilarity, englishNames);
        saveAllByDictionarySimilarity(draw, dictionarySimilarity);
    }

    private void saveAllByDictionarySimilarity(final Draw draw, final DictionarySimilarity similarityMap) {
        final List<Result> results = resultMapper.toEntityList(similarityMap, draw);
        resultRepository.saveAll(results);
    }

    @Override
    @Transactional(readOnly = true)
    public DrawResultResponse getResultByDrawId(final UUID drawId) {
        final List<Result> results = resultRepository.findByDrawId(drawId);
        validateEmptyResult(results);
        final Draw draw = drawService.loadEntity(drawId);
        return resultMapper.toDrawResultResponse(draw, results);
    }

    private void validateEmptyResult(final List<Result> results) {
        if (results.isEmpty()) {
            throw new DrawResultNotFoundException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GameResultResponse getResultByGameId(final UUID gameId) {
        final List<Result> results = resultRepository.findByGameId(gameId);
        final Game game = gameService.loadEntity(gameId);

        validateSizeOfResult(results, game);
        return resultMapper.toGameResultResponse(game, results);
    }

    private void validateSizeOfResult(final List<Result> results, final Game game) {
        if (isValidResultSize(results, game)) {
            throw new GameResultNotFoundException();
        }
    }

    private boolean isValidResultSize(final List<Result> results, final Game game) {
        return results.size() != game.getPlayerNum() * RESULT_PER_PLAYER_SIZE;
    }

    @Override
    public Result loadEntity(final UUID id) {
        return resultRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
