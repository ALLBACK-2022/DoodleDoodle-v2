package com.doodledoodle.backend.dictionary.entity;

import com.doodledoodle.backend.global.audit.AuditListener;
import com.doodledoodle.backend.global.audit.Auditable;
import com.doodledoodle.backend.global.audit.BaseTime;
import com.doodledoodle.backend.result.entity.Result;

import javax.persistence.*;

import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dictionary{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String koreanName;

  @Column(unique = true, nullable = false)
  private String englishName;

  @Column(unique = true, nullable = false)
  private String imgUrl;

  @OneToOne(fetch = FetchType.LAZY)
  private Result result;

  @Builder
  public Dictionary(String koreanName, String englishName, String imgUrl, Result result) {
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.imgUrl = imgUrl;
    this.result = result;
  }
}
