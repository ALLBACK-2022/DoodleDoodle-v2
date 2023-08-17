package com.doodledoodle.backend.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RandomGenerator {
    Random random;

    public RandomGenerator() {
        random = new Random();
    }

    public Long generateRandom() {
        return (long) (random.nextDouble() * 99 + 1);
    }
}
