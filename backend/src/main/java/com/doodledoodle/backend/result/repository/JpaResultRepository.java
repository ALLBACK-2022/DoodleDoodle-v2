package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaResultRepository extends JpaRepository<Result, Long>, ResultRepository {}
