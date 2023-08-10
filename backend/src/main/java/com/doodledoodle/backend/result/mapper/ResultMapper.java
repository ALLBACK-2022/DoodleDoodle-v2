package com.doodledoodle.backend.result.mapper;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.result.dto.collection.DictionaryMap;
import com.doodledoodle.backend.result.dto.collection.SimilarityMap;
import com.doodledoodle.backend.result.dto.response.DictionaryResultResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.dto.response.UserResultResponse;
import com.doodledoodle.backend.result.entity.Result;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultMapper {
    public Result toEntity(final Double similarity, final Draw draw, final Dictionary dictionary, final Game game) {
        return Result.builder()
                .similarity(similarity)
                .draw(draw)
                .dictionary(dictionary)
                .game(game)
                .build();
    }

    public List<Result> toEntityList(final SimilarityMap similarityMap, final Draw draw, final Game game, final DictionaryMap dictionaryMap) {
        return similarityMap.getKeySet().stream()
                .map(key -> toEntity(
                        similarityMap.getSimilarityByKey(key),
                        draw,
                        dictionaryMap.getDictionaryByKey(key),
                        game))
                .collect(Collectors.toList());
    }

    public DictionaryResultResponse toDictionaryResultResponse(final Result result) {
        Dictionary dictionary = result.getDictionary();
        return DictionaryResultResponse.builder()
                .id(result.getId())
                .similarity(result.getSimilarity())
                .koreanName(dictionary.getKoreanName())
                .englishName(dictionary.getEnglishName())
                .imageUrl(dictionary.getImageUrl())
                .build();
    }

    public UserResultResponse toUserResultResponse(final Result result) {
        Draw draw = result.getDraw();
        return UserResultResponse.builder()
                .drawId(draw.getId())
                .playerNo(draw.getPlayerNo())
                .imageUrl(draw.getImageUrl())
                .similarity(result.getSimilarity())
                .build();
    }

    public DrawResultResponse toEmptyDrawResponse() {
        return DrawResultResponse.builder().build();
    }

    public DrawResultResponse toDrawResultResponse(final Draw draw, final List<Result> results) {
        Result randomWordResult = toRandomWordResult(draw, results);
        return DrawResultResponse.builder()
                .imageUrl(draw.getImageUrl())
                .randomWord(toDictionaryResultResponse(randomWordResult))
                .topFive(toTopFive(toResultsWithoutRandomWord(results, randomWordResult)))
                .build();
    }

    private List<Result> toResultsWithoutRandomWord(final List<Result> results, final Result randomWordResult) {
        return results.stream()
                .filter(result -> !result.getId().equals(randomWordResult.getId()))
                .collect(Collectors.toList());
    }

    private Result toRandomWordResult(final Draw draw, final List<Result> results) {
        return results.stream()
                .filter(r -> r.getDictionary().getEnglishName().equals(draw.getGame().getEnglishName()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    public GameResultResponse toGameResultResponse(final Game game, final List<Result> results) {
        return GameResultResponse.builder()
                .randomWord(game.getEnglishName())
                .results(toUserResultResponseList(game.getEnglishName(), results))
                .build();
    }

    private List<UserResultResponse> toUserResultResponseList(final String englishName, final List<Result> results) {
        return results.stream()
                .filter(r -> r.getDictionary().getEnglishName().equals(englishName))
                .map(this::toUserResultResponse)
                .collect(Collectors.toList());
    }

    private List<DictionaryResultResponse> toTopFive(final List<Result> results) {
        return List.of(
                toDictionaryResultResponse(results.get(0)),
                toDictionaryResultResponse(results.get(1)),
                toDictionaryResultResponse(results.get(2)),
                toDictionaryResultResponse(results.get(3)),
                toDictionaryResultResponse(results.get(4)));
    }

    public GameResultResponse toEmptyGameResponse() {
        return GameResultResponse.builder().build();
    }
}
