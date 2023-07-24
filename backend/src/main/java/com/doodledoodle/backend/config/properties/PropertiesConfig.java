package com.doodledoodle.backend.config.properties;

import com.doodledoodle.backend.global.storage.S3StorageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({S3StorageProperties.class})
public class PropertiesConfig {
}
