package com.doodledoodle.backend.draw.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.game.service.GameService;
import com.doodledoodle.backend.support.database.DatabaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DatabaseTest
@DisplayName("Draw 서비스의")
public class DrawServiceTest {

    @Autowired private DrawService drawService;
    @Autowired private DrawRepository drawRepository;
    @Autowired private GameService gameService;
    @Autowired private GameRepository gameRepository;

    @Test
    @DisplayName("사진이 저징이 되고 ai 모델로 요청을 하는가")
    void saveDraw() {
        //given
        Long id = 1L;
        Game expectedGame =Game.builder().playerNum(1).englishName("skateboard").build();
        gameRepository.save(expectedGame);
        Game game = gameService.loadEntity(id);
        //when
        Draw draw = drawRepository.save(Draw.builder().game(game).playerNo(1).build());
        drawRepository.save(draw);
        //then
        assertThat(draw.getId()).isEqualTo(1L);
    }

}
