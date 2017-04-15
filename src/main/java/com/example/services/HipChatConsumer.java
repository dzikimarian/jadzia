package com.example.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.bus.*;


import java.util.function.Consumer;
import java.util.logging.Logger;


/**
 * Created by Michał on 15.04.2017.
 */
@Slf4j
@Service
public class HipChatConsumer implements Consumer<Event<String>>{

    @Override
    public void accept(Event<String> event) {
        log.debug("Event received");
    }
}
