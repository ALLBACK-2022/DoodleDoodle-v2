package com.doodledoodle.backend.result.mapper;

import com.doodledoodle.backend.result.entity.Result;
import org.springframework.stereotype.Component;

@Component
public class ResultMapper {
    public Result toEntity(Float similarity) { // todo 다른 필드 추가
        return Result.builder()
                .similarity(similarity)
                .build();
    }
}
