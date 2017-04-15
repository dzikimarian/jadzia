package com.example.foodproviders;

import com.example.entities.FoodItem;

import java.util.List;

/**
 * Created by Michał on 14.04.2017.
 */
public interface FoodProvider {
    List<FoodItem> getMenu();
    Boolean shouldOrder();
}
