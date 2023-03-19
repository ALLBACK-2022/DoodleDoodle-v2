package com.doodledoodle.backend.draw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Draw {
    @Id @GeneratedValue
    private Long id;
}
