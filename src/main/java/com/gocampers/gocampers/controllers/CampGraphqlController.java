package com.gocampers.gocampers.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.repository.CampInfoRepository;
// import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampGraphqlController {
        private final CampInfoRepository campInfoRepository;
    
        @QueryMapping
        public Iterable<CampInfo> allCampPaged(@Argument int page,@Argument int size){
            PageRequest pr = PageRequest.of(page, size);
            return campInfoRepository.findAll(pr);
        }

        @QueryMapping
        public CampInfo findCampById(@Argument int contentId){
            return campInfoRepository.findById(contentId).orElse(null);
        }
        //https://velog.io/@aidenshin/Querydsl-%EB%8F%99%EC%A0%81-%EC%BF%BC%EB%A6%AC
        // private Iterable<CampInfo> searchCamp(){
        //     BooleanBuilder builder = new BooleanBuilder();


        //     return null;
        // }
}
