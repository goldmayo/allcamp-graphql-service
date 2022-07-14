package com.gocampers.gocampers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.stereotype.Repository;

import com.gocampers.gocampers.domain.entity.CampInfo;

@Repository
@GraphQlRepository
// public interface CampInfoRepository extends JpaRepository<CampInfo,Integer> , QuerydslPredicateExecutor<CampInfo>{
    public interface CampInfoRepository extends JpaRepository<CampInfo,Integer>{
    
}
