package com.gocampers.gocampers.controllers;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.service.impl.CampServiceImpl;

import graphql.relay.Connection;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampGraphqlController {
        
        private final CampServiceImpl campServiceImpl;
        // private final Logger LOGGER = LoggerFactory.getLogger(CampGraphqlController.class);

        @QueryMapping
        public CampInfo findCampById(@Argument int contentId){
            return campServiceImpl.findCampById(contentId);
        }

        @QueryMapping
        public Connection<CampInfo> searchCamps(@Argument int first, @Argument String after, @Argument CampSearchParamsDto params){
            // LOGGER.info("params {}",params);
            return campServiceImpl.searchCamps(first, after, params);
        }

}
