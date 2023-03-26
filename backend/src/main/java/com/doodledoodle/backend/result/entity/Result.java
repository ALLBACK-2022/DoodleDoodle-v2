package com.doodledoodle.backend.result.entity;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Float simillarity;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dictionary_id")
  private Dictionary dictionary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id")
  private Game game;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "draw_id")
  private Draw draw;

  @Builder
  public Result(Float simillarity, Dictionary dictionary, Game game, Draw draw) {
    this.simillarity = simillarity;
    this.dictionary = dictionary;
    this.game = game;
    this.draw = draw;
  }
}
