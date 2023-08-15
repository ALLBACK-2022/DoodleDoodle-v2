package com.doodledoodle.backend.game.entity;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.global.audit.AuditListener;
import com.doodledoodle.backend.global.audit.Auditable;
import com.doodledoodle.backend.global.audit.BaseTime;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game implements Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dictionary dictionary;

    @Column(nullable = false)
    private Integer playerNum;

    @Setter
    @Embedded
    @Column(nullable = false)
    private BaseTime baseTime;

    @Builder
    public Game(final Integer playerNum) {
        this.playerNum = playerNum;
    }

    public void updateDictionary(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
