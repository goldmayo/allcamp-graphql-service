package com.gocampers.gocampers.service.impl;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.repository.CampInfoRepository;
import com.gocampers.gocampers.service.CampService;

@Service
public class CampServiceImpl implements CampService {
    private final static String BASE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping";
    private final Logger LOGGER = LoggerFactory.getLogger(CampServiceImpl.class);
    private final ApiKey API_KEY = ApiKey.getApiKey();
    @Autowired
    private CampInfoRepository campRepository;

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
}
