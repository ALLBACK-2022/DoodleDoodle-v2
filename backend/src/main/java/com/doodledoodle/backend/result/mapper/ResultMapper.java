package com.doodledoodle.backend.result.mapper;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.result.dto.response.DictionaryResultResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.dto.response.UserResultResponse;
import com.doodledoodle.backend.result.entity.DictionaryMap;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.entity.SimilarityMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultMapper {
    public Result toEntity(Double similarity, Draw draw, Dictionary dictionary, Game game) {
        return Result.builder()
                .similarity(similarity)
                .draw(draw)
                .dictionary(dictionary)
                .game(game)
                .build();
    }

    public List<Result> toEntityList(SimilarityMap similarityMap, Draw draw, Game game, DictionaryMap dictionaryMap) {
        return similarityMap.getKeySet().stream()
                .map(key -> toEntity(similarityMap.getSimilarityByKey(key),
                                    draw,
                                    dictionaryMap.getDictionaryByKey(key),
                                    game))
                .collect(Collectors.toList());
    }

    public DictionaryResultResponse toDictionaryResultResponse(Result result) {
        Dictionary dictionary = result.getDictionary();
        return DictionaryResultResponse.builder()
                .id(result.getId())
                .similarity(result.getSimilarity())
                .name(dictionary.getKoreanName())
                .engName(dictionary.getEnglishName())
                .imgUrl(dictionary.getImgUrl())
                .build();
    }

    public UserResultResponse toUserResultResponse(Result result) {
        Draw draw = result.getDraw();
        return UserResultResponse.builder()
                .drawId(draw.getId())
                .drawNo(draw.getDrawNo())
                .imgUrl(draw.getDoodle())
                .similarity(result.getSimilarity())
                .build();
    }

    public DrawResultResponse toDrawResultResponse(Draw draw, List<Result> results) {
        return DrawResultResponse.builder()
                .doodle(draw.getDoodle())
                .randomWord(toDictionaryResultResponse(results.get(0)))
                .topFive(toTopFive(results))
                .build();
    }

    public GameResultResponse toGameResultResponse(Game game, List<Result> results) {
        return GameResultResponse.builder()
                .randomWord(game.getRandomWord())
                .users(toUserResultResponseDtoList(results))
                .build();
    }

    private List<UserResultResponse> toUserResultResponseDtoList(List<Result> results) {
        return results.stream().map(this::toUserResultResponse).collect(Collectors.toList());
    }

    private List<DictionaryResultResponse> toTopFive(List<Result> results) {
        return List.of(
                toDictionaryResultResponse(results.get(1)),
                toDictionaryResultResponse(results.get(2)),
                toDictionaryResultResponse(results.get(3)),
                toDictionaryResultResponse(results.get(4)));
    }
}
