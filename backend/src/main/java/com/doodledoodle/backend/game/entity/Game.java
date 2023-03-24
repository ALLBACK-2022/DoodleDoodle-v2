package com.doodledoodle.backend.game.entity;

import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.global.entity.BaseEntity;
import com.doodledoodle.backend.result.entity.Result;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "GAME")
public class Game extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "game_id")
  private Long id;

  private Integer playerNum;

  @OneToMany(mappedBy = "game")
  @JoinColumn(name = "draw_id")
  private List<Draw> draws = new ArrayList<>();

  @OneToMany(mappedBy = "result")
  @JoinColumn(name = "result_id")
  private List<Result> results = new ArrayList<>();
}
