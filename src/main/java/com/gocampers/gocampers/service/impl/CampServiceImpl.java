package com.gocampers.gocampers.service.impl;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocampers.gocampers.config.ApiKey;
import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.repository.CampInfoRepository;
import com.gocampers.gocampers.service.CampService;
import com.gocampers.gocampers.connection.CursorUtil;

import graphql.relay.Connection;
import graphql.relay.DefaultConnection;
import graphql.relay.DefaultEdge;
import graphql.relay.DefaultPageInfo;
import graphql.relay.Edge;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampServiceImpl implements CampService {
    private final static String BASE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping";
    private final Logger LOGGER = LoggerFactory.getLogger(CampServiceImpl.class);
    private final ApiKey API_KEY = ApiKey.getApiKey();

    @Autowired
    private CampInfoRepository campRepository;
    
    private final CursorUtil cursorUtil;

    public List<CampInfo> fetchCampInfo(){
        URI uri  = UriComponentsBuilder.fromUriString(BASE_URL)
			.path("/basedList")
			.queryParam("ServiceKey",API_KEY)
			.queryParam("MobileOS","ETC")
			.queryParam("numOfRows","3048")
			.queryParam("MobileApp","AppTest")
			.queryParam("_type","json")
            .build(true)
            .encode(StandardCharsets.UTF_8)
            .toUri();
        LOGGER.info("start fetching...");
        RestTemplate restTemplate = new RestTemplate();
        String responseEntity = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectmapper =  new ObjectMapper();
        List<CampInfo> listCampInfo = null;
        try {
            JsonNode responseRoot = objectmapper.readTree(responseEntity);
            JsonNode reponseBody = responseRoot.get("response");
            JsonNode bodyRoot = reponseBody.get("body");
            JsonNode itemsRoot = bodyRoot.get("items");
            JsonNode item = itemsRoot.get("item");
            listCampInfo = objectmapper.readValue(item.toString(), new TypeReference<List<CampInfo>>(){});            
        } catch (Exception e) {
            LOGGER.warn(e.toString());
        }
        LOGGER.info("finish fetching.");
        return listCampInfo;
    }
   
    @Override
    public void getBasicCamp() {
        List<CampInfo> CampInfoList = fetchCampInfo();
        campRepository.saveAll(CampInfoList);
    }

    @Override
    public Page<CampInfo> allCampPaged(@Argument int page,@Argument int size){
        PageRequest pr = PageRequest.of(page, size);       
        return campRepository.findAll(pr);
    }
    @Override
    public CampInfo findCampById(@Argument int contentId){
        return campRepository.findById(contentId).orElse(null);
    }

    @Override
    public List<CampInfo> getCampInfoAfter(int id) {
        return campRepository.findAll().stream()
                .dropWhile(campInfo -> Integer.compare(campInfo.getContentId(),id) != 1)
                .collect(Collectors.toUnmodifiableList());
    }
    
    @Override
    public Connection<CampInfo> allCamps(@Argument int first, @Argument String after){
        List<Edge<CampInfo>> edges = getAllCampInfo(after)
        .stream()
        .map(campInfo -> new DefaultEdge<>(campInfo, cursorUtil.encode(campInfo.getContentId())))
        .limit(first)
        .collect(Collectors.toUnmodifiableList());
        // LOGGER.info("edges : {}", edges);
        
        var pageInfo = new DefaultPageInfo(
            cursorUtil.getFristCursorFrom(edges),
            cursorUtil.getLastCursorFrom(edges),
            after!= null,
            edges.size() >= first);
        
        return new DefaultConnection<>(edges,pageInfo);
    }
    
    @Override
    public List<CampInfo> getAllCampInfo(String after){
        // LOGGER.info("after : {}", after);
        if(after == null){
           return campRepository.findAll();
        }
        return getCampInfoAfter(cursorUtil.decode(after));
    }

    @Override
    public List<CampInfo> getAllCamps(int first, String after, CampSearchParamsDto params){
        return after == null
                ? campRepository.searchCamps(first, params)
                : campRepository.searchCampsAfterCursor(first, cursorUtil.decode(after), params);
    }
    
    @Override
    public Connection<CampInfo> searchCamps(@Argument int first,@Argument String after, @Argument CampSearchParamsDto params ){
        List<Edge<CampInfo>> edges = getAllCamps(first,after,params)
        .stream()
        .map(campInfo -> new DefaultEdge<>(campInfo, cursorUtil.encode(campInfo.getContentId())))
        .collect(Collectors.toUnmodifiableList());
        // LOGGER.info("edges : {}", edges);

        var pageInfo = new DefaultPageInfo(
            cursorUtil.getFristCursorFrom(edges),
            cursorUtil.getLastCursorFrom(edges),
            after!= null,
            edges.size() >= first);
        
        return new DefaultConnection<>(edges,pageInfo);
    }

}
