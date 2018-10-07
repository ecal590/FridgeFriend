package com.example.ebonycalloway.fridgefriend.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ebonycalloway.fridgefriend.Controller.MyDBHandler;
import com.example.ebonycalloway.fridgefriend.Model.ShoppingListValues;
import com.example.ebonycalloway.fridgefriend.POJO.Food;
import com.example.ebonycalloway.fridgefriend.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class ShoppingList extends AppCompatActivity implements ShoppingListValues{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        final MyDBHandler dbHandler;
        dbHandler = new MyDBHandler(this, null, null, 2);
        ListView foodLV = (ListView) findViewById(R.id.shoppingList);
        String foods = dbHandler.shoppingListToString("alpha");
        String[] foodValues = foods.split(";");
        if(foods != null) {
            ListAdapter foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foodValues);
            foodLV.setAdapter(foodAdapter);
            foodLV.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String foodName = String.valueOf(parent.getItemAtPosition(position)).split(":")[0];
                            String cost = String.valueOf(parent.getItemAtPosition(position)).split(":")[1];
                            String extras = dbHandler.selectFood(foodName, cost);
                            //TODO: Change amount to buy and recalculate total
                            //Intent i = new Intent(ShoppingList.this, AddItem.class);
                            //i.putExtra(Intent.EXTRA_TEXT, extras);
                            //startActivity(i);

                        }

                    }
            );
            foodLV.setOnItemLongClickListener(
                    new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                            final String foodN = String.valueOf(parent.getItemAtPosition(position)).split(":")[0];
                            final String foodD = String.valueOf(parent.getItemAtPosition(position)).split(":")[1];
                            AlertDialog.Builder alert = new AlertDialog.Builder(ShoppingList.this);
                            alert.setTitle("Delete from your Shopping List");
                            alert.setMessage("Do you want to delete the item from your shopping list?");
                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent i2 = new Intent(ShoppingList.this, ShoppingList.class);

                                    String tempFoodString = dbHandler.selectFood(foodN,foodD);
                                    Food newFood = new Food();
                                    String foodParts[] = tempFoodString.split(",");
                                    newFood.setName(foodParts[0]);
                                    newFood.setDescription(foodParts[1]);
                                    newFood.setAmountLeft(Integer.parseInt(foodParts[2]));
                                    newFood.setPrice(Integer.parseInt(foodParts[3]));
                                    newFood.setExpiration(foodParts[4]);
                                    newFood.setRating(Double.parseDouble(foodParts[5]));
                                    newFood.setShoppingList(false);
                                    dbHandler.addFood(newFood);
                                    startActivity(i2);
                                }
                            });

                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            alert.show();
                            return true;
                        }

                    }
            );
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"You don't need to go shopping",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void addFood(ArrayList<Food> ShoppingListMain, Food foodString) {
        ShoppingListMain.add(foodString);
    }

    @Override
    public void removeFood(ArrayList<Food> ShoppingListNames, int which) {
        ShoppingListNames.remove(ShoppingListNames.get(which));
    }

}
