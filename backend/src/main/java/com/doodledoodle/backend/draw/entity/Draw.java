package com.doodledoodle.backend.draw.entity;

import com.doodledoodle.backend.game.entity.Game;
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
public class Draw implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    public Draw(String imageUrl, Game game, Integer playerNo) {
        this.imageUrl = imageUrl;
        this.game = game;
        this.playerNo = playerNo;
    }

    public void updateImgUrl(String imgUrl) {
        this.imageUrl = imgUrl;
    }
}
