package com.example.services;

import com.example.foodproviders.FoodProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.bus.EventBus;

import java.util.logging.Logger;

/**
 * Created by Michał on 14.04.2017.
 */
@Slf4j
@Service
public class Ordering {

    private FoodProvider foodProvider;
    private EventBus eventBus;

    @Autowired
    public Ordering(FoodProvider foodProvider, EventBus eventBus){
        this.eventBus = eventBus;
        this.foodProvider = foodProvider;
    }

    //second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "0 0 9 * * 1-5")
    public void remind(){
        log.debug("Reminder sent");
    }


    @Scheduled(cron = "0 55 9 * * 1-5")
    public void order(){
        if(foodProvider.shouldOrder()){
            log.debug("Zamówienie wysłane");
        } else {
            log.debug("Zamówienie anulowane");
        }

    }
}
