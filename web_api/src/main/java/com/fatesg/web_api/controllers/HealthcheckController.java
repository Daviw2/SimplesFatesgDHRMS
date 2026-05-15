package com.fatesg.web_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/healthcheck")
public class HealthcheckController {
    @GetMapping() 
    public String healthcheck() {
        return "OK";
    }
}
