package com.example.controller;

import com.example.entities.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by micha on 28.03.2017.
 */
@RestController
public class TestController {
    @RequestMapping(value = "/")
    public String test(){
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote.toString();
    }
}
