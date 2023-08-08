package com.doodledoodle.backend.global.kafka;

public interface KafkaConsumer<T> {
    void consume(String message);
}
