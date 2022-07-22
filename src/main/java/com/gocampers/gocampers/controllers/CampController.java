package com.gocampers.gocampers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gocampers.gocampers.service.CampInfoFetchService;


@RestController
@RequestMapping("/api")
public class CampController {
    
    private final CampInfoFetchService campInfoFetchService;
    public CampController(CampInfoFetchService campInfoFetchService){
        this.campInfoFetchService = campInfoFetchService;
    }

    @GetMapping("/basic")
    public void getBasicCamp() {
        campInfoFetchService.getBasicCamp();
    }
}
