package com.doodledoodle.backend.result.mapper;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.result.dto.response.DictionaryResultResponseDto;
import com.doodledoodle.backend.result.dto.response.DrawResultResponseDto;
import com.doodledoodle.backend.result.dto.response.GameResultResponseDto;
import com.doodledoodle.backend.result.dto.response.UserResultResponseDto;
import com.doodledoodle.backend.result.entity.Result;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultMapper {
    public Result toEntity(Float similarity, Draw draw, Dictionary dictionary, Game game) {
        return Result.builder()
                .similarity(similarity)
                .draw(draw)
                .dictionary(dictionary)
                .game(game)
                .build();
    }

    public DictionaryResultResponseDto toDictionaryResultResponseDto(Result result) {
        Dictionary dictionary = result.getDictionary();
        return DictionaryResultResponseDto.builder()
                .id(result.getId())
                .similarity(result.getSimilarity())
                .name(dictionary.getName())
                .engName(dictionary.getEngName())
                .imgUrl(dictionary.getImgUrl())
                .build();
    }

    public UserResultResponseDto toUserResultResponseDto(Result result) {
        Draw draw = result.getDraw();
        return UserResultResponseDto.builder()
                .drawId(draw.getId())
                .drawNo(draw.getDrawNo())
                .imgUrl(draw.getDoodle())
                .similarity(result.getSimilarity())
                .build();
    }

    public DrawResultResponseDto toDrawResultResponseDto(Draw draw, List<Result> results) {
        return DrawResultResponseDto.builder()
                .doodle(draw.getDoodle())
                .randomWord(toDictionaryResultResponseDto(results.get(0)))
                .topFive(toTopFive(results))
                .build();
    }

    public GameResultResponseDto toGameResultResponseDto(Game game, List<Result> results) {
        return GameResultResponseDto.builder()
                .randomWord(game.getRandomWord())
                .users(toUserResultResponseDtos(results))
                .build();
    }

    private List<UserResultResponseDto> toUserResultResponseDtos(List<Result> results) {
        return results.stream().map(this::toUserResultResponseDto).collect(Collectors.toList());
    }

    private List<DictionaryResultResponseDto> toTopFive(List<Result> results) {
        return List.of(
                toDictionaryResultResponseDto(results.get(1)),
                toDictionaryResultResponseDto(results.get(2)),
                toDictionaryResultResponseDto(results.get(3)),
                toDictionaryResultResponseDto(results.get(4)));
    }
}
