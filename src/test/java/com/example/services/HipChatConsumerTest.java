package com.example.services;

import org.junit.Test;
import reactor.bus.Event;

import static org.junit.Assert.*;

/**
 * Created by Michał on 15.04.2017.
 */
public class HipChatConsumerTest {
    @Test
    public void accept() throws Exception {
        HipChatConsumer h = new HipChatConsumer();

        h.accept(new Event<String>("Accept"));

    }

}