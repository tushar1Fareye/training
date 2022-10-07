package com.fareye.training.controller;

import com.fareye.training.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("properties")
    public  String fetchProperties(@RequestParam String key) {
        return propertyService.getProperty(key);
    }
}
