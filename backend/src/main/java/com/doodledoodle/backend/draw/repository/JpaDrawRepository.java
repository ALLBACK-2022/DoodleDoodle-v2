package com.doodledoodle.backend.draw.repository;

import com.doodledoodle.backend.draw.entity.Draw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDrawRepository extends JpaRepository<Draw, Long>, DrawRepository {}
