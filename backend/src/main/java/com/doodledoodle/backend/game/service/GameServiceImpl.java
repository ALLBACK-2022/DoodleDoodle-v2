package com.doodledoodle.backend.game.service;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.dto.request.GameWordRequest;
import com.doodledoodle.backend.game.dto.response.GameWordResponse;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.mapper.GameMapper;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.global.dto.IdResponse;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameServiceImpl implements GameService {
    GameRepository gameRepository;
    GameMapper gameMapper;
    DictionaryService dictionaryService;

    @Override
    public IdResponse<UUID> createGame(final GameRequest gameRequest) {
        final Game game = gameRepository.save(gameMapper.toEntity(gameRequest));
        return new IdResponse<>(game.getId());
    }

    @Override
    @Transactional
    public GameWordResponse saveWord(final GameWordRequest gameWordRequest) {
        final Game game = loadEntity(gameWordRequest.getId());
        final Dictionary dictionary = dictionaryService.getDictionaryByKoreanName(gameWordRequest.getKoreanName());
        game.updateDictionary(dictionary);
        return gameMapper.toResponse(game);
    }

    @Override
    public Game loadEntity(final UUID id) {
        return gameRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
