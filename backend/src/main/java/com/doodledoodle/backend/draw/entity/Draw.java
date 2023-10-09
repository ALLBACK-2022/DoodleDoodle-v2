package com.doodledoodle.backend.draw.entity;

import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.audit.AuditListener;
import com.doodledoodle.backend.global.audit.Auditable;
import com.doodledoodle.backend.global.audit.BaseTime;
import com.github.f4b6a3.ulid.UlidCreator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@EqualsAndHashCode(of = "imageUrl")
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Draw implements Auditable {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    private String imageUrl;

    @Column(nullable = false)
    private Integer playerNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)")
    private Game game;

    @Setter
    @Embedded
    @Column(nullable = false)
    private BaseTime baseTime;

    @Builder
    public Draw(final String imageUrl, final Game game, final Integer playerNo) {
        this.imageUrl = imageUrl;
        this.game = game;
        this.playerNo = playerNo;
    }

    public void updateImgUrl(final String imgUrl) {
        this.imageUrl = imgUrl;
    }
}
