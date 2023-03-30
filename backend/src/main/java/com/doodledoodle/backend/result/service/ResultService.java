package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.result.dto.response.DrawResultResponseDto;
import com.doodledoodle.backend.result.dto.response.GameResultResponseDto;

import java.util.Map;

public interface ResultService {
    void saveResult(Long drawId, Map<String, Float> result);
    DrawResultResponseDto getResultByDrawId(Long drawId);
    GameResultResponseDto getResultByGameId(Long gameId);
}
