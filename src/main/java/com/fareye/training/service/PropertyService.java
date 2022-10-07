package com.fareye.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    private Environment environment;

    public String getProperty(String key) {
        return  environment.getProperty(key);
    }
}
