package com.doodledoodle.backend.result.controller;

import static com.doodledoodle.backend.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.doodledoodle.backend.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.doodledoodle.backend.result.dto.response.DictionaryResultResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.dto.response.UserResultResponse;
import com.doodledoodle.backend.result.service.ResultService;
import com.doodledoodle.backend.result.service.ResultServiceImpl;
import com.doodledoodle.backend.support.docs.RestDocumentTest;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@DisplayName("Result 컨트롤러의")
@WebMvcTest(ResultController.class)
class ResultControllerTest extends RestDocumentTest {
    @MockBean private ResultService resultService;

    @Test
    @DisplayName("Game PK를 통한 조회가 수행되는가")
    void getResultByGameId() throws Exception {
        //given
        GameResultResponse expected = new GameResultResponse("사과",
                List.of(new UserResultResponse(UUID.randomUUID(), 1, "http://someResultImageUrlToS3", 90.0),
                        new UserResultResponse(UUID.randomUUID(), 2, "http://someResultImageUrlToS3", 98.0)));
        given(resultService.getResultByGameId(any())).willReturn(expected);

        //when
        ResultActions perform =
                mockMvc.perform(
                        get("/results/games/" + UUID.randomUUID())
                                .contentType(MediaType.APPLICATION_JSON));
        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.random_word").isString())
                .andExpect(jsonPath("$.results").isArray());

        //docs
        perform.andDo(print())
                .andDo(document("get result by game id", getDocumentRequest(), getDocumentResponse()));
    }

    @Test
    @DisplayName("Draw PK를 통한 조회가 수행되는가")
    void getResultByDrawId() throws Exception {
        //given
        DrawResultResponse expected = new DrawResultResponse("http://someDoodleImageUrlToS3", new DictionaryResultResponse(
                UUID.randomUUID(), 100.0, "사과", "Apple", "http://someDoodleImageUrlToS3"),
                List.of(new DictionaryResultResponse(UUID.randomUUID(), 90.0, "오렌지", "Orange", "http://someDoodleImageUrlToS3"),
                        new DictionaryResultResponse(UUID.randomUUID(), 80.0, "배", "Pear", "http://someDoodleImageUrlToS3"),
                        new DictionaryResultResponse(UUID.randomUUID(), 70.0, "원", "Circle", "http://someDoodleImageUrlToS3"),
                        new DictionaryResultResponse(UUID.randomUUID(), 60.0, "햄버거", "Hamburger", "http://someDoodleImageUrlToS3")));
        given(resultService.getResultByDrawId(any())).willReturn(expected);

        //when
        ResultActions perform =
                mockMvc.perform(
                        get("/results/draws/" + UUID.randomUUID())
                                .contentType(MediaType.APPLICATION_JSON));
        //then
        perform.andExpect(status().isOk());

        //docs
        perform.andDo(print())
                .andDo(document("get result by draw id", getDocumentRequest(), getDocumentResponse()));
    }
}
