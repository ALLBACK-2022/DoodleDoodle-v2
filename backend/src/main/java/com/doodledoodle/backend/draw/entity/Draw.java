package com.doodledoodle.backend.draw.entity;

import com.doodledoodle.backend.game.entity.Game;
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
@EqualsAndHashCode(of = "imageUrl")
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Draw implements Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String imageUrl;

    @Column(nullable = false)
    private Integer playerNo;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
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
