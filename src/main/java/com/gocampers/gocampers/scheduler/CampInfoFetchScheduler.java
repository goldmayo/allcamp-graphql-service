package com.gocampers.gocampers.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gocampers.gocampers.service.impl.CampServiceImpl;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CampInfoFetchScheduler {

    private final CampServiceImpl campServiceImpl;
    private final Logger LOGGER = LoggerFactory.getLogger(CampServiceImpl.class);
    
    public CampInfoFetchScheduler(CampServiceImpl campServiceImpl){
        this.campServiceImpl = campServiceImpl;
    }
    
    @Async
    @Scheduled(cron = "0 30 11 L * ?", zone = "Asia/Seoul")
    public void fetchCampInfo(){
        LOGGER.info("getBasicCamp time  : "+LocalTime.now());
        LOGGER.info("getBasicCamp thread: "+Thread.currentThread().getName());
        campServiceImpl.getBasicCamp();
    }
}
