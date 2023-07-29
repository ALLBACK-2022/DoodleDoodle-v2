package com.doodledoodle.backend.game.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.global.dto.IdResponse;
import com.doodledoodle.backend.support.database.DatabaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DatabaseTest
@DisplayName("Game 서비스의")
public class GameServiceTest {

    @Autowired private GameService gameService;
    @Autowired private GameRepository gameRepository;

    @Test
    @DisplayName("인원수가 저장되는가")
    @BeforeEach
    void creatGame(){
        //given
        int playerNum = 1;

        //when
        IdResponse<Long> response = gameService.createGame(new GameRequest(playerNum));

        //then
        Game gameEntity = gameRepository.findById(response.getId()).orElseThrow();

        assertThat(response.getId()).isEqualTo(gameEntity.getId());
    }

    @Test
    @DisplayName("단어가 저장이 되는가")
    void saveWord() {
        //given
        Long id = 1L;
        String skateboard = "skateboard";
        //when
        Game game = gameService.loadEntity(id);
        //then
        game.updateEnglishName(skateboard);

        assertThat(game.getEnglishName()).isEqualTo(skateboard);
    }
}
