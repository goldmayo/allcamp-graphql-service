package com.gocampers.gocampers.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.domain.paging.ConnectionQuery;
import com.gocampers.gocampers.repository.CampInfoRepository;
import com.gocampers.gocampers.service.CampService;
import com.gocampers.gocampers.connection.CursorUtil;
import com.gocampers.gocampers.connection.impl.CampConnectionImpl;

import graphql.relay.Connection;
import graphql.relay.DefaultEdge;
import graphql.relay.DefaultPageInfo;
import graphql.relay.Edge;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampServiceImpl implements CampService {
   
    @Autowired
    private CampInfoRepository campRepository;
    
    private final CursorUtil cursorUtil;

    @Override
    public CampInfo findCampById(@Argument int contentId){
        return campRepository.findById(contentId).orElse(null);
    }

    @Override
    public ConnectionQuery<CampInfo> getForwardAllCamps(int first, String after, CampSearchParamsDto params){
        if(after != null){
            cursorUtil.decode(after);
        }
        return after == null
                ? campRepository.searchCampsQueryForward(first, params)
                : campRepository.searchCampsQueryAfterCursor(first, cursorUtil.decode(after), params);
    }
    @Override
    public ConnectionQuery<CampInfo> getBackwardAllCamps(int last, String before, CampSearchParamsDto params){
        return before == null
                ? campRepository.searchCampsQueryBackward(last, params)
                : campRepository.searchCampsQueryBeforeCursor(last, cursorUtil.decode(before), params);
    }

    @Override
    public Connection<CampInfo> searchForwardCamps(@Argument int first,@Argument String after, @Argument CampSearchParamsDto params ){
        ConnectionQuery<CampInfo> result = getForwardAllCamps(first, after, params);
        List<CampInfo> edgeChunk = result.getQueryResults();
        int totalCounts = result.getTotalCounts();
        List<Edge<CampInfo>> edges = edgeChunk
        .stream()
        .map(campInfo -> new DefaultEdge<>(campInfo, cursorUtil.encode(campInfo.getContentId())))
        .collect(Collectors.toUnmodifiableList());

        var pageInfo = new DefaultPageInfo(
            cursorUtil.getFristCursorFrom(edges),
            cursorUtil.getLastCursorFrom(edges),
            after!= null,
            edges.size() >= first);
        return new CampConnectionImpl<>(totalCounts, edges, pageInfo);
    }

    @Override
    public Connection<CampInfo> searchBackwardCamps(@Argument int last,@Argument String before, @Argument CampSearchParamsDto params ){
        ConnectionQuery<CampInfo> result = getBackwardAllCamps(last, before, params);
        List<CampInfo> edgeChunk = result.getQueryResults();
        int totalCounts = result.getTotalCounts();
        List<Edge<CampInfo>> edges = edgeChunk
        .stream()
        .map(campInfo -> new DefaultEdge<>(campInfo, cursorUtil.encode(campInfo.getContentId())))
        .collect(Collectors.toUnmodifiableList());

        var pageInfo = new DefaultPageInfo(
            cursorUtil.getFristCursorFrom(edges),
            cursorUtil.getLastCursorFrom(edges),
            edges.size() >= last,
            before != null);
        return new CampConnectionImpl<>(totalCounts, edges, pageInfo);
    }

    @Override
    public Connection<CampInfo> searchCamps(@Argument Integer first, @Argument String after, @Argument Integer last, @Argument String before, @Argument CampSearchParamsDto params ){
        if(first != null && last == null){
            return searchForwardCamps(first,after,params);
        }
        else if(last != null && first == null ){
            return searchBackwardCamps(last,before,params);
        }
        else{
            return new CampConnectionImpl<>(0, null, new DefaultPageInfo(
                null,
                null,
                false,
                false));
        }
        
    }
}
