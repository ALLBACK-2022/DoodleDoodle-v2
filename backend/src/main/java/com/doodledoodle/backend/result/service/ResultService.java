package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.dto.response.DrawResultResponse;
import com.doodledoodle.backend.result.dto.response.GameResultResponse;
import com.doodledoodle.backend.result.entity.Result;

import java.util.UUID;

public interface ResultService extends EntityLoader<Result, UUID> {
    void saveResults(final ResultKafkaResponse resultKafkaResponse);

    DrawResultResponse getResultByDrawId(final UUID drawId);

    GameResultResponse getResultByGameId(final UUID gameId);
}
