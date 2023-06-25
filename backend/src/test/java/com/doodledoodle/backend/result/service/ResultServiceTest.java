package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.repository.ResultRepository;
import com.doodledoodle.backend.support.database.DatabaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@DatabaseTest
@DisplayName("Result 서비스의") // todo Draw와 Game의 로직이 완성된 후 작성하기 (종속적임)
class ResultServiceTest {
    @Autowired private ResultService resultService;
    @Autowired private ResultRepository resultRepository;
    @Autowired private DrawRepository drawRepository;
    @Autowired private GameRepository gameRepository;

    @Test
    @DisplayName("결과 저장이 수행되는가")
    void saveResults() {
        //given

        //when

        //then

    }

    @Nested
    @DisplayName("결과 조회 로직 중")
    class selectResult {
        @Test
        @DisplayName("Draw PK로 조회가 수행되는가")
        void getResultByDrawId() {
            //given

            //when

            //then

        }

        @Test
        @DisplayName("Result PK로 조회가 수행되는가")
        void getResultByGameId() {
            //given

            //when

            //then

        }

        @Test
        @DisplayName("Result PK로 조회가 수행되는가")
        void loadEntity() {
            //given

            //when

            //then

        }
    }
}
