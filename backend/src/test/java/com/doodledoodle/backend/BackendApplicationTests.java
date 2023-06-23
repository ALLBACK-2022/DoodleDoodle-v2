package com.doodledoodle.backend;

import com.doodledoodle.backend.config.kafka.KafkaConsumerConfig;
import com.doodledoodle.backend.config.kafka.KafkaProducerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;

@ComponentScan(excludeFilters  = {@ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE, classes = {KafkaConsumerConfig.class, KafkaProducerConfig.class})})
@SpringBootTest("spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration")
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class BackendApplicationTests {
	@Test
	void contextLoads() {}
}
