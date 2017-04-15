package com.example.foodproviders;

import com.example.entities.FoodItem;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by micha on 06.04.2017.
 */
@Slf4j
public class LuLunchTest {
    @Test
    public void extractMenuFromDOM_parses_document_correctly() throws Exception {
        LuLunch lu = new LuLunch();

        Document doc = null;

        try {
            doc = Jsoup.connect("http://www.lublin-lunch.pl/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<FoodItem> menu = lu.extractMenuFromDOM(doc);

        log.debug(menu.get(4).toString());

        assertNotNull(menu);
    }

    @Test
    public void isFoodItem_finds_valid_food(){
        LuLunch lu = new LuLunch();

        Element tr = new Element("tr");
        tr.html("<td> </td><td>Jedzenie</td><td><strong>12</strong> zł</td>");
        FoodItem invalidFood = new FoodItem("wrong", 0);
        FoodItem actualFood = lu.getFoodItem(tr).orElse(invalidFood);

        assertEquals("Jedzenie", actualFood.getName());
        assertEquals(12, (int)actualFood.getPrice());
    }

    @Test
    public void isFoodItem_ignores_invalid_food(){
        LuLunch lu = new LuLunch();

        Element tr = new Element("tr");
        tr.html("<td> </td><td>Jedzenie</td><td><strong>Cena</strong></td>");

        assertEquals(Optional.empty(), lu.getFoodItem(tr));
    }

}