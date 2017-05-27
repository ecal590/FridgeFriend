package com.example.ebonycalloway.fridgefriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton settingB = (ImageButton) findViewById(R.id.settingsButton);
        Button addB = (Button) findViewById(R.id.addItemButton);
        Button viewLibraryB = (Button) findViewById(R.id.viewLibraryButton);

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



    }
}
