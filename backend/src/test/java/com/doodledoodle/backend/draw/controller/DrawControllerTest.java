package com.doodledoodle.backend.draw.controller;

import static com.doodledoodle.backend.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.doodledoodle.backend.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.doodledoodle.backend.draw.dto.request.DrawRequest;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.service.DrawService;
import com.doodledoodle.backend.support.docs.RestDocumentTest;
import com.doodledoodle.backend.support.utils.MultipartFileSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

@DisplayName("Draw 컨트롤러의")
@WebMvcTest(DrawController.class)
public class DrawControllerTest extends RestDocumentTest {

    @MockBean private DrawService drawService;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("그림을 저장하는 API가 수행되는가")
    void saveDraw() throws Exception {
        //given
        MockMultipartFile image = new MockMultipartFile("image", "test.png", "multipart/mixed", "some png".getBytes());
        DrawResponse expected = new DrawResponse(1L);
        given(drawService.saveDraw(any())).willReturn(expected);

        SimpleModule module = new SimpleModule();
        module.addSerializer(MultipartFile.class, new MultipartFileSerializer());
        objectMapper.registerModule(module);

        //when
        ResultActions perform =
                mockMvc.perform(
                        post("/draws")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                    toRequestBody(
                                        new DrawRequest(1L,1,image))));

        //then
        perform.andExpect(status().isOk());

        //docs
        perform.andDo(print())
            .andDo(document("save draw and send to kafka", getDocumentRequest(), getDocumentResponse()));

    }
}
