package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GameResultResponse {
    private String randomWord;
    private List<UserResultResponse> users;
}
