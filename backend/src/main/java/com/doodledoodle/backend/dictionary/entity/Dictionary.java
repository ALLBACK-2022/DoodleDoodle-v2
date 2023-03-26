package com.doodledoodle.backend.dictionary.entity;

import com.doodledoodle.backend.global.audit.BaseEntity;
import com.doodledoodle.backend.result.entity.Result;

import javax.persistence.*;

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

  private String name;

  private String engName;

  private String imgUrl;

  @Enumerated(EnumType.STRING)
  private StorageType storageType;

  @OneToOne(fetch = FetchType.LAZY)
  private Result result;

  @Builder
  public Dictionary(String name, String engName, String imgUrl, StorageType storageType, Result result) {
    this.name = name;
    this.engName = engName;
    this.imgUrl = imgUrl;
    this.storageType = storageType;
    this.result = result;
  }
}
