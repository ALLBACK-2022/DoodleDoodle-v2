package com.doodledoodle.backend.draw.entity;

import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.audit.AuditListener;
import com.doodledoodle.backend.global.audit.Auditable;

import javax.persistence.*;

import com.doodledoodle.backend.global.audit.BaseTime;
import lombok.*;

@Getter
@Entity
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Draw implements Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @Column(nullable = false)
  private String doodle;

  @Column(nullable = false)
  private Integer drawNo;

  @JoinColumn
  @ManyToOne(fetch = FetchType.LAZY)
  private Game game;

  @Setter
  @Embedded
  @Column(nullable = false)
  private BaseTime baseTime;

  @Builder
  public Draw(String doodle, Game game, Integer drawNo) {
    this.doodle = doodle;
    this.game = game;
    this.drawNo = drawNo;
  }

  public void update(Long id, String doodle){
    this.id = id;
    this.doodle = doodle;
  }
}
