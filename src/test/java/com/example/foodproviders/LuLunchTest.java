package com.example.foodproviders;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by micha on 06.04.2017.
 */
public class LuLunchTest {
    @Test
    public void getData() throws Exception {
        LuLunch lu = new LuLunch();
        ArrayList<String> menu = lu.getData();

        assertNotNull(menu);
    }

}