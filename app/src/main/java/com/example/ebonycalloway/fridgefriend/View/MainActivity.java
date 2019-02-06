package com.example.ebonycalloway.fridgefriend.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ebonycalloway.fridgefriend.Model.ShoppingListValues;
import com.example.ebonycalloway.fridgefriend.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton settingB = findViewById(R.id.settingsButton);
        Button addB = findViewById(R.id.addItemButton);
        Button viewLibraryB = findViewById(R.id.viewLibraryButton);
        Button shoppingListB = findViewById(R.id.shoppingListButton);

        settingB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, Settings.class);
                startActivity(i);
            }

        });

        addB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, AddItem.class);
                startActivity(i);
            }

        });

        viewLibraryB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, FoodLibrary.class);
                startActivity(i);
            }

        });

        shoppingListB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ShoppingList.class);
                startActivity(i);
            }
        });


//TODO: Full content backup in manifest?
        //TODO: Action-View intent filer, indexable by Google Search
        //TODO: New activity, food library, stores all past foods you have had and add them to the fridge or shopping list from there!!! DO THIS
    }
}
