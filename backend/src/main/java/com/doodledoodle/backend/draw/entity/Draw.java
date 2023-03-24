package com.doodledoodle.backend.draw.entity;

import com.doodledoodle.backend.game.entity.Game;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DRAW")
public class Draw extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "draw_id")
  private Long id;

  @Column(nullable = false)
  private String doodle;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  @OneToMany(mappedBy = "draw")
  @JoinColumn(name = "result_id")
  private List<Result> results = new ArrayList<>();
}
