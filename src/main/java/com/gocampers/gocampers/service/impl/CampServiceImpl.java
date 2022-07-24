package com.gocampers.gocampers.service.impl;

import java.util.List;
import java.util.stream.Collectors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
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
    // private final Logger LOGGER = LoggerFactory.getLogger(CampServiceImpl.class);

    @Autowired
    private CampInfoRepository campRepository;
    
    private final CursorUtil cursorUtil;

    @Override
    public CampInfo findCampById(@Argument int contentId){
        return campRepository.findById(contentId).orElse(null);
    }

    @Override
    public ConnectionQuery<CampInfo> getAllCamps(int first, String after, CampSearchParamsDto params){
        return after == null
                ? campRepository.searchCampsQuery(first, params)
                : campRepository.searchCampsQueryAfterCursor(first, cursorUtil.decode(after), params);
    }

    @Override
    public Connection<CampInfo> searchCamps(@Argument int first,@Argument String after, @Argument CampSearchParamsDto params ){
        ConnectionQuery<CampInfo> result = getAllCamps(first, after, params);
        List<CampInfo> edgeChunk = result.getQueryResults();
        int totalCounts = result.getTotalCounts();
        List<Edge<CampInfo>> edges = edgeChunk
        .stream()
        .map(campInfo -> new DefaultEdge<>(campInfo, cursorUtil.encode(campInfo.getContentId())))
        .collect(Collectors.toUnmodifiableList());
        // LOGGER.info("edges : {}", edges);

        var pageInfo = new DefaultPageInfo(
            cursorUtil.getFristCursorFrom(edges),
            cursorUtil.getLastCursorFrom(edges),
            after!= null,
            edges.size() >= first);
        return new CampConnectionImpl<>(totalCounts, edges, pageInfo);
    }

}
