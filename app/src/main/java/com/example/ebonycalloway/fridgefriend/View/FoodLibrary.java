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
import com.example.ebonycalloway.fridgefriend.R;

public class FoodLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_library);

        final MyDBHandler dbHandler;
        dbHandler = new MyDBHandler(this, null, null, 1);
        String foodString = dbHandler.databaseToString("alpha");
        ListView foodLV = (ListView) findViewById(R.id.foodListView);
        if(!foodString.equals("")) {
            String[] foods = foodString.split(";");
            ListAdapter foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
            foodLV.setAdapter(foodAdapter);
        }
        foodLV.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String foodName = String.valueOf(parent.getItemAtPosition(position)).split(":")[0];
                        String description = String.valueOf(parent.getItemAtPosition(position)).split(":")[1];
                        String extras = dbHandler.selectFood(foodName,description);
                        Intent i = new Intent(FoodLibrary.this, AddItem.class);
                        i.putExtra(Intent.EXTRA_TEXT, extras);
                        startActivity(i);

                    }

                }
        );
        foodLV.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                        final String foodN = String.valueOf(parent.getItemAtPosition(position)).split(":")[0];
                        final String foodD = String.valueOf(parent.getItemAtPosition(position)).split(":")[1];
                        AlertDialog.Builder alert = new AlertDialog.Builder(FoodLibrary.this);
                        alert.setTitle("Delete or Shopping List");
                        alert.setMessage("Do you want to delete the item or add it to your shopping list?");
                        CharSequence choice[] = new CharSequence[]{"Delete","Shopping List","Cancel"};
                        alert.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dbHandler.deleteFood(foodN, foodD);
                                Toast.makeText(FoodLibrary.this, foodN + " was removed", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                Intent i2 = new Intent(FoodLibrary.this, FoodLibrary.class);
                                startActivity(i2);
                            }

                        });

                        alert.setNegativeButton("ShoppingList", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String extras = dbHandler.selectFood(foodN,foodD);
                                Intent i2 = new Intent(FoodLibrary.this, ShoppingList.class);
                                i2.putExtra(Intent.EXTRA_TEXT,extras);
                                startActivity(i2);
                            }
                        });
                        alert.show();
                        return true;
                    }

                }
        );
    }
}
