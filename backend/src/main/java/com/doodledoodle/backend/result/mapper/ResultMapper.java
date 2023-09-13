package com.doodledoodle.backend.result.mapper;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.result.dto.response.DictionaryResultResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.dto.response.UserResultResponse;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.entity.collection.DictionarySimilarity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultMapper {
    public Result toEntity(final Dictionary dictionary, final Double similarity, final Draw draw, final Game game, final Boolean isRepresent) {
        return Result.builder()
                .dictionary(dictionary)
                .similarity(similarity)
                .draw(draw)
                .game(game)
                .isRepresent(isRepresent)
                .build();
    }

    public List<Result> toEntityList(final DictionarySimilarity dictionarySimilarity, final Draw draw, final Boolean isRepresent) {
        return dictionarySimilarity.getDictionaries().stream()
                .map(key -> toEntity(key, dictionarySimilarity.getSimilarityByDictionary(key), draw, draw.getGame(), isRepresent))
                .collect(Collectors.toList());
    }

    private DictionaryResultResponse toDictionaryResultResponse(final Result result) {
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

    public DrawResultResponse toDrawResultResponse(final Draw draw, final List<Result> results) {
        Result randomWordResult = toRandomWordResult(results);
        return DrawResultResponse.builder()
                .imageUrl(draw.getImageUrl())
                .randomWord(toDictionaryResultResponse(randomWordResult))
                .topFive(toTopFive(toResultsWithoutRandomWord(results)))
                .build();
    }

    private List<Result> toResultsWithoutRandomWord(final List<Result> results) {
        return results.stream()
                .filter(Result::isNotRepresent)
                .collect(Collectors.toList());
    }

    private Result toRandomWordResult(final List<Result> results) {
        return results.stream()
                .filter(Result::getIsRepresent)
                .findAny()
                .orElseThrow(EntityNotFoundException::new);
    }

    public GameResultResponse toGameResultResponse(final Game game, final List<Result> results) {
        Dictionary dictionary = game.getDictionary();
        return GameResultResponse.builder()
                .randomWord(dictionary.getKoreanName())
                .results(toUserResultResponseList(dictionary.getEnglishName(), results))
                .build();
    }

    private List<UserResultResponse> toUserResultResponseList(final String englishName, final List<Result> results) {
        return results.stream()
                .filter(r -> r.isRepresentResultByEnglishName(englishName))
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
}
