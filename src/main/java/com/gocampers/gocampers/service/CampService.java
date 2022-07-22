package com.gocampers.gocampers.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;

import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;

import graphql.relay.Connection;

public interface CampService {
    // public void getBasicCamp();

    public List<CampInfo> getCampInfoAfter(int id);

    public Page<CampInfo> allCampPaged(@Argument int page,@Argument int size);

    public CampInfo findCampById(@Argument int contentId);

    public Connection<CampInfo> allCamps(@Argument int first,@Argument String after);
    
    public List<CampInfo> getAllCampInfo(String after);

    public List<CampInfo> getAllCamps(int first, String after, CampSearchParamsDto params);

    public Connection<CampInfo> searchCamps(@Argument int first,@Argument String after, @Argument CampSearchParamsDto params );

}