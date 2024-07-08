package com.ishan.liyanage.stock_market_prediction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public String ping() {
        return "Pong!";
    }
}
