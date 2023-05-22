package com.doodledoodle.backend.result.mapper;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.result.dto.response.DictionaryResultResponseDto;
import com.doodledoodle.backend.result.dto.response.UserResultResponseDto;
import com.doodledoodle.backend.result.entity.Result;
import org.springframework.stereotype.Component;

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
}
