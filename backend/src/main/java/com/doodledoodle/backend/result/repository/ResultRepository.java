package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long>, ResultQueryRepository {}
