package com.doodledoodle.backend.game.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.dto.request.GameWordRequest;
import com.doodledoodle.backend.game.dto.response.GameWordResponse;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.game.repository.GameRepositoryStandard;
import com.doodledoodle.backend.global.dto.IdResponse;
import com.doodledoodle.backend.support.database.DatabaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@DatabaseTest
@DisplayName("Game 서비스의")
class GameServiceTest {

    @Autowired private GameService gameService;
    @Autowired private GameRepository gameRepository;

    @Test
    @DisplayName("인원수가 저장되는가")
    void creatGame(){
        //given
        int playerNum = 1;

        //when
        IdResponse<UUID> response = gameService.createGame(new GameRequest(playerNum));

        //then
        Game game = gameRepository.findById(response.getId()).orElseThrow();

        assertThat(response.getId()).isEqualTo(game.getId());
    }

    @Test
    @DisplayName("단어가 저장이 되는가")
    void saveWord() {
        //given
        Game game = gameRepository.save(new Game(1));
        GameWordRequest request = new GameWordRequest(game.getId(), "스케이트보드");

        //when
        GameWordResponse response = gameService.saveWord(request);

        //then
        Game findGame = gameRepository.findById(game.getId()).orElseThrow();
        assertThat(response.getEnglishName()).isEqualTo(findGame.getDictionary().getEnglishName());
    }
}
