package com.doodledoodle.backend.game.entity;

import com.doodledoodle.backend.global.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType. IDENTITY)
  private Long id;

  private String randomWord;

  private Integer playerNum;

  @Builder
  public Game(String randomWord, Integer playerNum) {
    this.randomWord = randomWord;
    this.playerNum = playerNum;
  }
}
