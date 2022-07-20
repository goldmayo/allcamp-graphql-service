package com.gocampers.gocampers.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
        private final Logger LOGGER = LoggerFactory.getLogger(CampGraphqlController.class);

        @QueryMapping
        public Page<CampInfo> allCampPaged(@Argument int page,@Argument int size){
            return campServiceImpl.allCampPaged(page, size);
        }

        @QueryMapping
        public CampInfo findCampById(@Argument int contentId){
            return campServiceImpl.findCampById(contentId);
        }
        
        @QueryMapping
        public Connection<CampInfo> allCamps(@Argument int first,@Argument String after){
            return campServiceImpl.allCamps(first, after);
        }

        @QueryMapping
        public Connection<CampInfo> searchCamps(@Argument int first, @Argument String after, @Argument CampSearchParamsDto params){
            LOGGER.info("params {}",params);
            return campServiceImpl.searchCamps(first, after, params);
        }

}
