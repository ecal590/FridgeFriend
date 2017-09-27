package com.example.ebonycalloway.fridgefriend.View;

import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity implements ShoppingListValues{
    ShoppingListValues ShoppingListMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        final MyDBHandler dbHandler;
        dbHandler = new MyDBHandler(this, null, null, 1);
        String foodString = dbHandler.databaseToString("rating");
        ListView foodLV = (ListView) findViewById(R.id.shoppingList);

        Food foodTemp = new Food();
        foodTemp.setName(foodString.split(",")[0]);
        foodTemp.setDescription(foodString.split(",")[1]);
        foodTemp.setAmountLeft(Integer.parseInt(foodString.split(",")[2]));
        foodTemp.setPrice(Double.parseDouble(foodString.split(",")[3]));
        foodTemp.setExpiration(foodString.split(",")[4]);
        foodTemp.setRating(Double.parseDouble(foodString.split(",")[5]));
        foodTemp.setHealthGroup(Integer.parseInt(foodString.split(",")[6]));
        String[] foodStrings = ShoppingListNames.toString().split("}");
        if(!foodString.equals("")) {
            ShoppingListMain.ShoppingListNames.add(foodTemp);
            ListAdapter foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,foodStrings);
            foodLV.setAdapter(foodAdapter);
        }

        foodLV.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String foodName = String.valueOf(parent.getItemAtPosition(position)).split(",")[0];
                        String description = String.valueOf(parent.getItemAtPosition(position)).split(",")[1];
                        String extras = dbHandler.selectFood(foodName,description);
                        Intent i = new Intent(ShoppingList.this, AddItem.class);
                        i.putExtra(Intent.EXTRA_TEXT, extras);
                        startActivity(i);

                    }

                }
        );
        foodLV.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id){
                        final String foodN = String.valueOf(parent.getItemAtPosition(position)).split(":")[0];
                        final String foodD = String.valueOf(parent.getItemAtPosition(position)).split(":")[1];
                        AlertDialog.Builder alert = new AlertDialog.Builder(ShoppingList.this);
                        alert.setTitle("Delete from your Shopping List");
                        alert.setMessage("Do you want to delete the item from your shopping list?");
                        alert.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent i2 = new Intent(ShoppingList.this, ShoppingList.class);
                                ShoppingListMain.removeFood(ShoppingListNames, position);
                                startActivity(i2);
                            }
                        });

                        alert.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
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
    }

    @Override
    public void addFood(ArrayList<Food> ShoppingListMain, Food foodString) {
        ShoppingListMain.add((Food) foodString);
    }

    @Override
    public void removeFood(ArrayList<Food> ShoppingListNames, int which) {
        ShoppingListNames.remove(ShoppingListNames.get(which));
    }

}
