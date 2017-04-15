package com.example.foodproviders;

import com.example.entities.FoodItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by micha on 06.04.2017.
 */

@Service
public class LuLunch implements FoodProvider{

    public List<FoodItem> extractMenuFromDOM(Document doc)
    {
        Elements basicMenu = doc.select("table.table tr");

        return basicMenu.stream()
                .map(this::getFoodItem)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Optional<FoodItem> getFoodItem(Element tr)
    {
        Elements td = tr.select("td");
        Elements priceBox = td.get(2).select("strong");

        if(priceBox.size() > 0 && priceBox.text().matches("^-?\\d+$")){
            return
                    Optional.of(
                            new FoodItem(td.get(1).text(),Integer.parseInt(priceBox.select("strong").text()))
                    );

        }
        return Optional.empty();
    }

    @Override
    public List<FoodItem> getMenu()
    {
        Document doc = null;

        try {
            doc = Jsoup.connect("http://www.lublin-lunch.pl/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.extractMenuFromDOM(doc);
    }

    @Override
    public Boolean shouldOrder()
    {
        return true;
    }
}
