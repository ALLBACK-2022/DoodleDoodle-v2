package com.doodledoodle.backend.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")                      // 모든 경로에 대해 CORS 설정을 적용
                .allowedOrigins("*")                               // 모든 오리진을 허용 (실제 운영환경에서는 필요한 오리진으로 제한하는 것이 좋습니다.)
                .allowedMethods("GET", "POST", "PUT", "DELETE")    // 허용할 HTTP 메서드
                .allowedHeaders("*")                               // 모든 요청 헤더 허용
                .allowCredentials(false)                           // 인증 정보 (쿠키 등)을 허용하지 않음
                .maxAge(3600);                                     // preflight 요청의 유효 시간 설정 (1시간)
    }
}
