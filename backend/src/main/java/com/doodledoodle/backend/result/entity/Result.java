package com.doodledoodle.backend.result.entity;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.audit.AuditListener;
import com.doodledoodle.backend.global.audit.Auditable;
import com.doodledoodle.backend.global.audit.BaseTime;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@EqualsAndHashCode(of = "draw")
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes =
        {@Index(name = "game_id_index", columnList = "game_id"),
                @Index(name = "draw_id_index", columnList = "draw_id")})
public class Result implements Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dictionary dictionary;

    @Column(nullable = false)
    private Double similarity;

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
    public Result(final Dictionary dictionary, final Double similarity, final Game game, final Draw draw) {
        this.dictionary = dictionary;
        this.similarity = similarity;
        this.game = game;
        this.draw = draw;
    }
}
