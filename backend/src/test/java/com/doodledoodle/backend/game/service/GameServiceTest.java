package com.doodledoodle.backend.game.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.dto.request.GameWordRequest;
import com.doodledoodle.backend.game.dto.response.GameWordResponse;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.global.dto.IdResponse;
import com.doodledoodle.backend.support.database.DatabaseTest;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DatabaseTest
@DisplayName("Game 서비스의")
public class GameServiceTest {

    @Autowired private GameService gameService;
    @Autowired private GameRepository gameRepository;
    @Autowired EntityManager em;


    @Test
    @DisplayName("인원수가 저장되는가")
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
        em.flush();
        em.clear();
        //given
        Game game = gameRepository.save(new Game("skateboard", 1));
        GameWordRequest request = new GameWordRequest(game.getId(), "스케이트보드");

        //when
        GameWordResponse response = gameService.saveWord(request);

        //then
        Game gameEntity = gameRepository.findById(game.getId()).orElseThrow();
        assertThat(response.getEnglishName()).isEqualTo(gameEntity.getEnglishName());
    }
}
