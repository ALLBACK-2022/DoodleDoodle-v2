package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaResultRepository extends JpaRepository<Result, Long>, ResultRepository {}
