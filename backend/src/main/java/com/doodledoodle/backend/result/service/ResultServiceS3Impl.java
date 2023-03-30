package com.doodledoodle.backend.result.service;

import com.doodledoodle.backend.result.dto.response.DrawResultResponseDto;
import com.doodledoodle.backend.result.dto.response.GameResultResponseDto;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.mapper.ResultMapper;
import com.doodledoodle.backend.result.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceS3Impl implements ResultService {
    private final ResultRepository resultRepository;
    private final ResultMapper resultMapper;

    @Override
    public void saveResult(Long drawId, Map<String, Float> result) {
        List<Result> results = result.keySet().stream()
                .map(key -> resultMapper.toEntity(result.get(key)))
                .collect(Collectors.toList());

        resultRepository.saveAll(results);
    }

    @Override
    public DrawResultResponseDto getResultByDrawId(Long drawId) {
        return null;
    }

    @Override
    public GameResultResponseDto getResultByGameId(Long gameId) {
        return null;
    }
}
