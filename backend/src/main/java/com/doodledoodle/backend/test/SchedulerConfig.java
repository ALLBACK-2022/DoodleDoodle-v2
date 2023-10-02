package com.doodledoodle.backend.test;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {
    private final TestService testService;

//    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24, initialDelay = 3000)   /* 3초후 시작, 24시간 후 업데이트 */
    void runTestDataJob() {
        testService.dataInsert();
    }
}
