package com.doodledoodle.backend.game.repository;

import com.doodledoodle.backend.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGameRepository extends JpaRepository<Game, Long>, GameRepository {}
