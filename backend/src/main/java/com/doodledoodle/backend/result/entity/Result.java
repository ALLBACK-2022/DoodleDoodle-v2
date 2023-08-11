package com.doodledoodle.backend.result.entity;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.audit.AuditListener;
import com.doodledoodle.backend.global.audit.Auditable;
import com.doodledoodle.backend.global.audit.BaseTime;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@EqualsAndHashCode(of = {"dictionary", "draw"})
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double similarity;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dictionary dictionary;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Draw draw;

    @Setter
    @Embedded
    @Column(nullable = false)
    private BaseTime baseTime;

    @Builder
    public Result(Double similarity, Dictionary dictionary, Game game, Draw draw) {
        this.similarity = similarity;
        this.dictionary = dictionary;
        this.game = game;
        this.draw = draw;
    }
}
