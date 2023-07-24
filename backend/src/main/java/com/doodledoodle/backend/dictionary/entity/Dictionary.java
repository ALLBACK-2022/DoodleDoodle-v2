package com.doodledoodle.backend.dictionary.entity;

import com.doodledoodle.backend.result.entity.Result;
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "dictionary")
    private Result result;

    @Builder
    public Dictionary(String koreanName, String englishName, String imageUrl, Result result) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.imageUrl = imageUrl;
        this.result = result;
    }
}
