package com.doodledoodle.backend.game.service;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.dto.request.GameWordRequest;
import com.doodledoodle.backend.game.dto.response.GameWordResponse;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.mapper.GameMapper;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.dto.IdResponse;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameService implements EntityLoader<Game, Long> {
    GameRepository gameRepository;
    GameMapper gameMapper;
    DictionaryService dictionaryService;

    public IdResponse<Long> createGame(final GameRequest requestDto) {
        Game game = gameRepository.save(gameMapper.toEntity(requestDto));
        return new IdResponse<>(game.getId());
    }

    public GameWordResponse saveWord(final GameWordRequest gameWordRequest) {
        Game game = loadEntity(gameWordRequest.getId());
        Dictionary dictionary = dictionaryService.getDictionary(gameWordRequest.getKoreanName());
        game.updateEnglishName(dictionary.getEnglishName());
        gameRepository.save(game);
        return gameMapper.toResponse(game);
    }

    @Override
    public Game loadEntity(final Long id) {
        return gameRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
