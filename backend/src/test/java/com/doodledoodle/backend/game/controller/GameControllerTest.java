package com.doodledoodle.backend.game.controller;

import static com.doodledoodle.backend.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.doodledoodle.backend.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.doodledoodle.backend.game.dto.request.GameRequest;
import com.doodledoodle.backend.game.dto.request.GameWordRequest;
import com.doodledoodle.backend.game.dto.response.GameWordResponse;
import com.doodledoodle.backend.game.service.GameService;
import com.doodledoodle.backend.global.dto.IdResponse;
import com.doodledoodle.backend.support.docs.RestDocumentTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@DisplayName("Game 컨트롤러의")
@WebMvcTest(GameController.class)
public class GameControllerTest extends RestDocumentTest {
    @MockBean private GameService gameService;

    @Test
    @DisplayName("인원수가 저장되는 API가 수행되는가")
    void createGame() throws Exception {
        //given
        IdResponse<Long> expected = new IdResponse<>(1L);
        given(gameService.createGame(any())).willReturn(expected);

        //when
        ResultActions perform =
                mockMvc.perform(
                        post("/games")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        toRequestBody(
                                            new GameRequest(3))));

        //then
        perform.andExpect(status().isOk());

        //docs
        perform.andDo(print())
            .andDo(document("save player number", getDocumentRequest(), getDocumentResponse()));
    }
    @Test
    @DisplayName("단어를 선택하는 API가 수행되는가")
    void saveWord() throws Exception {
        //given
        GameWordResponse expected = new GameWordResponse("skateboard");
        given(gameService.saveWord(any())).willReturn(expected);

        //when
        ResultActions perform =
                mockMvc.perform(
                        post("/games/random-words")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        toRequestBody(
                                            new GameWordRequest(1L,"스케이트보드"))));
        //then
        perform.andExpect(status().isOk());

        //docs
        perform.andDo(print())
            .andDo(document("set random word", getDocumentRequest(), getDocumentResponse()));
    }
}
