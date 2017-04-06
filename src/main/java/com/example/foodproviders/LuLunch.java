package com.example.foodproviders;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 06.04.2017.
 */
public class LuLunch {
    Document doc;

    public ArrayList<String> getData() {

        try {
            doc = Jsoup.connect("http://www.lublin-lunch.pl/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Elements basicMenu = doc.select("table.table tr");

        ArrayList<String> basicMenuList = new ArrayList<String>();

        basicMenu.forEach(tr -> {



            basicMenuList.add(tr.text());
            System.out.println(tr.text());
        });

        return new ArrayList<String>();
    }

    //move to separate class
    public Boolean isFoodItem(Element tr)
    {
        Elements td = tr.select("td");
        Elements priceBox = td.get(2).child(0).select("strong");

        return (priceBox.size() > 0 && priceBox.text().matches("^-?\\d+$"));
    }
}
