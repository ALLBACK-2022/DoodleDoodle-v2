package com.doodledoodle.backend.dictionary.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
