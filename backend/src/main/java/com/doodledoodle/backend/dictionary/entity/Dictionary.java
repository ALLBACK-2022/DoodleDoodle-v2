package com.doodledoodle.backend.dictionary.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 서비스 특성상 초기 SQL 파일로 데이터 저장 후 스프링 코드 상에서 조회만 되는 엔티티입니다.
 */

@Getter
@Entity
@EqualsAndHashCode(of = "englishName")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dictionary {
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String koreanName;

    @Column(unique = true, nullable = false)
    private String englishName;

    @Column(unique = true, nullable = false)
    private String imageUrl;
}
