package com.example.ebonycalloway.fridgefriend.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.ebonycalloway.fridgefriend.Controller.MyDBHandler;
import com.example.ebonycalloway.fridgefriend.POJO.Food;
import com.example.ebonycalloway.fridgefriend.R;

public class AddItem extends AppCompatActivity {
    MyDBHandler dbHandler;
    EditText foodName;
    EditText foodDescription;
    EditText amountLeft;
    EditText price;
    EditText expiration;
    RatingBar ratingBar;
    Spinner spinner;

    Button add;
    Food newFood;
    //TODO: Which inputs are mandatory

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        foodName = (EditText) findViewById(R.id.nameEditText);
        foodDescription = (EditText) findViewById(R.id.descriptionEditText);
        amountLeft = (EditText) findViewById(R.id.amountLeftEditText);
        price = (EditText) findViewById(R.id.priceEditText);
        expiration = (EditText) findViewById(R.id.expirationEditText);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        spinner = (Spinner) findViewById(R.id.groupSpinner);
        add = (Button) findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFood();
            }
        });
    }

    public void addFood(){
        newFood = new Food();
        newFood.setName(foodName.getText().toString());
        newFood.setDescription(foodDescription.getText().toString());
        newFood.setAmountLeft(Double.valueOf(amountLeft.getText().toString()));
        newFood.setPrice(Double.valueOf(price.getText().toString()));
        String thing = expiration.getText().toString();
        newFood.setExpiration(thing);
        newFood.setRating((double)ratingBar.getRating());
        newFood.setHealthGroup((String) spinner.getSelectedItem());
        dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.addFood(newFood);

        Intent i = new Intent(AddItem.this, FoodLibrary.class);
        startActivity(i);
    }
}
