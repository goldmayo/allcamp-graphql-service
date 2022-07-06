package com.gocampers.gocampers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;


@Getter
@Component
public class ApiKey {
    @Value("${api-key}")
    private String key;

    private static ApiKey instance = new ApiKey();

    private ApiKey(){}

    public static ApiKey getApiKey(){
        if(instance == null){
            instance = new ApiKey();
        }
        return instance;
    }
}
