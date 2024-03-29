package com.gocampers.gocampers.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gocampers.gocampers.service.CampInfoFetchService;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CampInfoFetchScheduler {

    private final CampInfoFetchService campInfoFetchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CampInfoFetchScheduler.class);
    
    public CampInfoFetchScheduler(CampInfoFetchService campInfoFetchService){
        this.campInfoFetchService = campInfoFetchService;
    }
    
    @Async
    @Scheduled(cron = "0 0 3 * * *", zone = "Asia/Seoul")
    public void fetchCampInfo(){
        LOGGER.info("getBasicCamp time  : "+LocalTime.now());
        LOGGER.info("getBasicCamp thread: "+Thread.currentThread().getName());
        campInfoFetchService.getBasicCamp();
    }
}
