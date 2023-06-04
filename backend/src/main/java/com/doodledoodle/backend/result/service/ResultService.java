package com.doodledoodle.backend.result.service;

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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ResultService {
    ResultRepository resultRepository;
    ResultMapper resultMapper;
    DrawService drawService;
    DictionaryService dictionaryService;
    GameService gameService;

    public void saveResult(Long drawId, Map<String, Float> result) {
        Draw draw = drawService.loadEntity(drawId);

        List<Result> results = result.keySet().stream()
                .map(key -> resultMapper.toEntity(
                        result.get(key),
                        draw,
                        dictionaryService.getEntityByEngName(key),
                        draw.getGame()))
                .collect(Collectors.toList());

        resultRepository.saveAll(results);
    }

    public DrawResultResponseDto getResultByDrawId(Long drawId) {
        List<Result> results = resultRepository.findByDrawIdOrderBySimilarityDesc(drawId);
        Draw draw = drawService.loadEntity(drawId);

        return resultMapper.toDrawResultResponseDto(draw, results);
    }

    public GameResultResponseDto getResultByGameId(Long gameId) {
        List<Result> results = resultRepository.findByGameIdOrderBySimilarityDesc(gameId);
        Game game = gameService.loadEntity(gameId);

        return resultMapper.toGameResultResponseDto(game, results);
    }
}
