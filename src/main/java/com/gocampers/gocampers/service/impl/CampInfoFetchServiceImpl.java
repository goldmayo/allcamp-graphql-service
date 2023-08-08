package com.gocampers.gocampers.service.impl;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocampers.gocampers.config.ApiKey;
import com.gocampers.gocampers.config.BasicApiPath;
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.domain.entity.CampInfo.ProjectContentIdAndModifiedDate;
import com.gocampers.gocampers.service.CampInfoFetchService;
import com.gocampers.gocampers.repository.CampInfoRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampInfoFetchServiceImpl implements CampInfoFetchService {
    private final ApiKey API_KEY;
    private final BasicApiPath BASE_URL;
    
    private final Logger LOGGER = LoggerFactory.getLogger(CampInfoFetchServiceImpl.class);
    
    @Autowired
    private CampInfoRepository campRepository;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    @Override
    public List<CampInfo> fetchCampInfo(){
        URI uri  = UriComponentsBuilder.fromUriString(BASE_URL.getPath())
        .path("/basedList")
        .queryParam("ServiceKey",API_KEY.getKey())
        .queryParam("MobileOS","ETC")
        .queryParam("numOfRows","3700")
        .queryParam("MobileApp","AppTest")
        .queryParam("_type","json")
        .build(true)
        .encode(StandardCharsets.UTF_8)
        .toUri();
        LOGGER.info("uri : {}", uri);
        LOGGER.info("Data fetching start.");
        RestTemplate restTemplate = new RestTemplate();
        String responseEntity = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectmapper =  new ObjectMapper();
        List<CampInfo> listCampInfo = null;
        try {
            JsonNode responseRoot = objectmapper.readTree(responseEntity);
            JsonNode reponseBody = responseRoot.get("response");
            JsonNode bodyRoot = reponseBody.get("body");
            JsonNode numOfRows = bodyRoot.get("numOfRows");
            JsonNode itemsRoot = bodyRoot.get("items");
            JsonNode item = itemsRoot.get("item");
            listCampInfo = objectmapper.readValue(item.toString(), new TypeReference<List<CampInfo>>(){});            
            LOGGER.info("Data fetching succeded.\n total fetched data count: {}",numOfRows);
        } catch (Exception e) {
            LOGGER.warn("Data fetching failed : {}",e.toString());
        }
        LOGGER.info("Data fetching finished.");
        return listCampInfo;
    }
   
    @Override
    public boolean getBasicCamp() {
        LOGGER.info("start getBasicCamp().");
        List<CampInfo> campInfoList = fetchCampInfo();
        
        List<ProjectContentIdAndModifiedDate> localContentIdAndModifiedtime = campRepository.findAllProjectContentIdAndModifiedDateBy();
        
        Map<Integer,String> localContentIdAndModifiedTimeMap = localContentIdAndModifiedtime.stream()
                        .collect(Collectors.toMap(
                                CampInfo.ProjectContentIdAndModifiedDate::getContentId,
                                CampInfo.ProjectContentIdAndModifiedDate::getModifiedtime));

        Set<Integer> localContentIds = localContentIdAndModifiedTimeMap.keySet();

        Set<Integer> localContentIdsWillBeDelete = localContentIds;
        List<CampInfo> localContentdAndModifiedtimeWillBeUpdate = new ArrayList<>();
        List<CampInfo> localContentdAndModifiedtimeWillBeCreate = new ArrayList<>();

        if(!campInfoList.isEmpty()){
            for (CampInfo fetched : campInfoList) {
                if(localContentIdAndModifiedTimeMap.containsKey(fetched.getContentId())){
                    //로컬 O fetch O => 갱신
                    localContentIdsWillBeDelete.remove(fetched.getContentId());
                    Date fetchedDate;
                    Date existDate;
                    try {
                        fetchedDate = fetched.getModifiedtime() != null ? format.parse(fetched.getModifiedtime()) : null;
                        existDate = localContentIdAndModifiedTimeMap.get(fetched.getContentId()) != null ?
                                    format.parse(localContentIdAndModifiedTimeMap.get(fetched.getContentId())) : null;
                        if (fetchedDate != null && existDate != null && fetchedDate.after(existDate)) {
                            LOGGER.info("update local data to fetched data");
                            localContentdAndModifiedtimeWillBeUpdate.add(fetched);
                        }
                    } catch (ParseException e) {
                        LOGGER.warn("Data save failed: {}",e.toString());
                    }
                }
                if(!localContentIdAndModifiedTimeMap.containsKey(fetched.getContentId())){
                    //로컬 X fetch O => 추가
                    localContentdAndModifiedtimeWillBeCreate.add(fetched);
                }
            }
            
            if(!localContentdAndModifiedtimeWillBeUpdate.isEmpty()){
                campRepository.saveAll(localContentdAndModifiedtimeWillBeUpdate);
            }
            if(!localContentdAndModifiedtimeWillBeCreate.isEmpty()){
                campRepository.saveAll(localContentdAndModifiedtimeWillBeCreate);
            }
            //로컬 O fetch X => 삭제
            if(!localContentIdsWillBeDelete.isEmpty()){
                campRepository.deleteAllById(localContentIdsWillBeDelete);
            }
            LOGGER.info("end getBasicCamp().");
            return true;
        }
        return false;
    }

}
