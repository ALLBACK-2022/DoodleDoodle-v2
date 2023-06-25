package com.doodledoodle.backend.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {
    private final Random random = new Random();

    public Long generateRandom() {
        return (long) (random.nextDouble() * 99 + 1);
    }
}
