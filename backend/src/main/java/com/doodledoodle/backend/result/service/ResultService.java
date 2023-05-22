package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.service.DrawService;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.service.GameService;
import com.doodledoodle.backend.result.dto.response.DrawResultResponseDto;
import com.doodledoodle.backend.result.dto.response.GameResultResponseDto;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.mapper.ResultMapper;
import com.doodledoodle.backend.result.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final ResultMapper resultMapper;
    private final DrawService drawService;
    private final DictionaryService dictionaryService;
    private final GameService gameService;

    public void saveResult(Long drawId, Map<String, Float> result) {
        Draw draw = drawService.loadEntity(drawId);

        List<Result> results = result.keySet().stream()
                .map(key -> {
                    Dictionary dictionary = dictionaryService.getEntityByEngName(key);
                    return resultMapper.toEntity(result.get(key), draw, dictionary, draw.getGame());
                }).collect(Collectors.toList());

        resultRepository.saveAll(results);
    }

    public DrawResultResponseDto getResultByDrawId(Long drawId) {
        List<Result> results = resultRepository.findByDrawIdOrderBySimilarityDesc(drawId);
        Draw draw = drawService.loadEntity(drawId);

        return new DrawResultResponseDto(
                draw.getDoodle(),
                resultMapper.toDictionaryResultResponseDto(results.get(0)),
                List.of(
                    resultMapper.toDictionaryResultResponseDto(results.get(1)),
                    resultMapper.toDictionaryResultResponseDto(results.get(2)),
                    resultMapper.toDictionaryResultResponseDto(results.get(3)),
                    resultMapper.toDictionaryResultResponseDto(results.get(4))));
    }

    public GameResultResponseDto getResultByGameId(Long gameId) {
        List<Result> results = resultRepository.findByGameIdOrderBySimilarityDesc(gameId);
        Game game = gameService.loadEntity(gameId);

        return new GameResultResponseDto(
                game.getRandomWord(),
                results.stream()
                        .map(resultMapper::toUserResultResponseDto)
                        .collect(Collectors.toList()));
    }
}
