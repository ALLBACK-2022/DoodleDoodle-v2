package com.doodledoodle.backend.dictionary.controller;

import static com.doodledoodle.backend.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.doodledoodle.backend.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.dictionary.service.DictionaryServiceImpl;
import com.doodledoodle.backend.support.docs.RestDocumentTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@DisplayName("Dictionary 컨트롤러의")
@WebMvcTest(DictionaryController.class)
class DictionaryControllerTest extends RestDocumentTest {
    @MockBean private DictionaryService dictionaryService;

    @Test
    @DisplayName("랜덤 단어를 가져오는 API가 수행되는가")
    void getRandomDictionary() throws Exception {
        //given
        DictionaryResponse expected = new DictionaryResponse("바나나");
        given(dictionaryService.getRandomDictionary()).willReturn(expected);

        //when
        ResultActions perform =
                mockMvc.perform(
                        get("/dictionaries/random-words")
                                .contentType(MediaType.APPLICATION_JSON));
        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.korean_name").isString());

        //docs
        perform.andDo(print())
                .andDo(document("get random dictionary word", getDocumentRequest(), getDocumentResponse()));
    }
}
