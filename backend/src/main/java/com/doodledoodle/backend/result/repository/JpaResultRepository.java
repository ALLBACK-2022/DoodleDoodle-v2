package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaResultRepository extends JpaRepository<Result, Long>, ResultRepository {
//    List<Result> saveAll(List<Result> results);
}
