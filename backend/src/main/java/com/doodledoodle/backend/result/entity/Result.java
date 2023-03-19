package com.doodledoodle.backend.result.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Result {
    @Id @GeneratedValue
    private Long id;
}
