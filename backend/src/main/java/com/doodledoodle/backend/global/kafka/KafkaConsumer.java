package com.doodledoodle.backend.global.kafka;

public interface KafkaConsumer<T> {
    void consume(final String message);

    T readMessage(final String message);
}
