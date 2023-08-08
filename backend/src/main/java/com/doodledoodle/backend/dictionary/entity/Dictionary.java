package com.doodledoodle.backend.dictionary.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@EqualsAndHashCode(of = "englishName")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String koreanName;

    @Column(unique = true, nullable = false)
    private String englishName;

    @Column(unique = true, nullable = false)
    private String imageUrl;

    @Builder
    public Dictionary(String koreanName, String englishName, String imageUrl) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.imageUrl = imageUrl;
    }
}
