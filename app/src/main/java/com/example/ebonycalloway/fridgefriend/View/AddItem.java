package com.example.ebonycalloway.fridgefriend.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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

        //Get Intent Info
        Intent i = getIntent();
        if(i.hasExtra(Intent.EXTRA_TEXT)) {
            String[] values = i.getStringExtra(Intent.EXTRA_TEXT).split(",");

            foodName.setText(values[0]);
            foodDescription.setText(values[1]);
            amountLeft.setText(values[2]);
            price.setText(values[3]);
            expiration.setText(values[4]);
            ratingBar.setRating(Float.parseFloat(values[5]));
            int tempInt = Integer.parseInt(values[6].split(";")[0]);
            spinner.setSelection(tempInt);
        }
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
        newFood.setHealthGroup(spinner.getSelectedItemPosition());
        dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.addFood(newFood);
        //TODO: Expiration date?
        Intent i = new Intent(AddItem.this, FoodLibrary.class);
        finish();
        startActivity(i);
    }
}
//TODO: Put date of expiration not the number of days for each food, expiration reminder