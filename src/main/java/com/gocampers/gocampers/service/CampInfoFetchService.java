package com.gocampers.gocampers.service;

import java.util.List;

import com.gocampers.gocampers.domain.entity.CampInfo;

public interface CampInfoFetchService {

    public List<CampInfo> fetchCampInfo();

    public void getBasicCamp();
}
