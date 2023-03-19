package com.doodledoodle.backend.dictionary.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dictionary {
    @Id @GeneratedValue
    private Long id;
}
