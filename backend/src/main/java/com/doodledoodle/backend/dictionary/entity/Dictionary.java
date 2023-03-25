package com.doodledoodle.backend.dictionary.entity;

import com.doodledoodle.backend.global.entity.BaseEntity;
import com.doodledoodle.backend.result.entity.Result;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DICTIONARY")
public class Dictionary extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "dictionary_id")
  private Long id;

  @Column(nullable = false)
  private String engName;

  @Column(nullable = false)
  private Integer imgUrl;

  @OneToOne
  private Result result;
}
