package com.doodledoodle.backend.game.entity;

import com.doodledoodle.backend.global.audit.AuditListener;
import com.doodledoodle.backend.global.audit.Auditable;
import com.doodledoodle.backend.global.audit.BaseTime;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Entity
@Where(clause = "deleted_at is null")
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String englishName;

    @Column(nullable = false)
    private Integer playerNum;

    @Setter
    @Embedded
    @Column(nullable = false)
    private BaseTime baseTime;

    @Builder
    public Game(String englishName, Integer playerNum) {
        this.englishName = englishName;
        this.playerNum = playerNum;
    }

    public void updateEnglishName(String englishName) {
        this.englishName = englishName;
    }
}
