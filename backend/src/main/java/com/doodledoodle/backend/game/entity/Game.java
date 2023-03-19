package com.doodledoodle.backend.game.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Game {
    @Id @GeneratedValue
    private Long id;
}
