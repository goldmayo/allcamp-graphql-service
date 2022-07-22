package com.gocampers.gocampers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;


@Getter
@Component
public class BasicApiPath {
    @Value("${basic-api-path}")
    private String path;

    private static BasicApiPath instance = new BasicApiPath();

    private BasicApiPath(){}

    public static BasicApiPath getBasicApiPath(){
        if(instance == null){
            instance = new BasicApiPath();
        }
        return instance;
    }
}
