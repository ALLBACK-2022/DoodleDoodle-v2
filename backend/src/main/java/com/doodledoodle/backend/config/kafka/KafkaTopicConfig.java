package com.doodledoodle.backend.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic commitTopic() {
        return TopicBuilder
                .name("doodledoodle.to.ai.draw")
                .build();
    }
}
