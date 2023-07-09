package com.doodledoodle.backend.game.entity;

import com.doodledoodle.backend.global.audit.AuditListener;

import javax.persistence.*;

import com.doodledoodle.backend.global.audit.Auditable;
import com.doodledoodle.backend.global.audit.BaseTime;
import lombok.*;

@Getter
@Entity
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game implements Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType. IDENTITY)
  private Long id;

  private String randomWord;

  @Column(nullable = false)
  private Integer playerNum;

  @Setter
  @Embedded
  @Column(nullable = false)
  private BaseTime baseTime;

  @Builder
  public Game(String randomWord, Integer playerNum) {
    this.randomWord = randomWord;
    this.playerNum = playerNum;
  }

  public void update(String randomWord){
    this.randomWord = randomWord;
  }
}
