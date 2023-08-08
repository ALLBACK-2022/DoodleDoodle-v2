package com.doodledoodle.backend.global.kafka;

public interface KafkaProducer<T> {
    void send(T request);
}
