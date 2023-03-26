package com.doodledoodle.backend.dictionary.entity;

import com.doodledoodle.backend.global.entity.BaseEntity;
import com.doodledoodle.backend.result.entity.Result;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dictionary extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String engName;

  private Integer imgUrl;

  @OneToOne(fetch = FetchType.LAZY)
  private Result result;

  @Builder
  public Dictionary(String engName, Integer imgUrl, Result result) {
    this.engName = engName;
    this.imgUrl = imgUrl;
    this.result = result;
  }
}
