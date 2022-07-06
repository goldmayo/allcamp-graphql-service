package com.gocampers.gocampers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gocampers.gocampers.service.CampService;

@RestController
@RequestMapping("/api")
public class CampController {
    
    private final CampService campService;

    public CampController(CampService campService){
        this.campService = campService;
    }

    @GetMapping("/basic")
    public void getBasicCamp() {
        campService.getBasicCamp();
    }
}
