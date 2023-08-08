package com.gocampers.gocampers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.domain.entity.CampInfo.ProjectContentIdAndModifiedDate;

@GraphQlRepository
public interface CampInfoRepository extends JpaRepository<CampInfo,Integer>, CustomQueryRepository{
    List<ProjectContentIdAndModifiedDate> findAllProjectContentIdAndModifiedDateBy();
}