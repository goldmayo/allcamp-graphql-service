package com.gocampers.gocampers.service;

import org.springframework.graphql.data.method.annotation.Argument;

import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.domain.paging.ConnectionQuery;

import graphql.relay.Connection;

public interface CampService {
    public CampInfo findCampById(@Argument int contentId);
    public ConnectionQuery<CampInfo> getAllCamps(int first, String after, CampSearchParamsDto params);
    public Connection<CampInfo> searchCamps(@Argument int first,@Argument String after, @Argument CampSearchParamsDto params );

}