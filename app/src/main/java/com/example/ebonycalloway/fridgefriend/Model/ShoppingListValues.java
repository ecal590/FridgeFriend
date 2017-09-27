package com.example.ebonycalloway.fridgefriend.Model;

import com.example.ebonycalloway.fridgefriend.POJO.Food;
import com.example.ebonycalloway.fridgefriend.View.ShoppingList;

import java.util.ArrayList;

/**
 * Created by ebonycalloway on 9/12/17.
 */

public interface ShoppingListValues {
    ArrayList<Food> ShoppingListNames = new ArrayList<>();

    public void addFood(ArrayList<Food> ShoppingListNames, Food foodString);
    public void removeFood(ArrayList<Food> ShoppingListNames, int which);
}
