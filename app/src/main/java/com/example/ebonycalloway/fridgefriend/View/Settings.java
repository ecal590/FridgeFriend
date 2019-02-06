package com.example.ebonycalloway.fridgefriend.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ebonycalloway.fridgefriend.R;

public class Settings extends AppCompatActivity {

    ToggleButton remindPics;
    ToggleButton remindExpire;
    ToggleButton remindLow;
    EditText daysExpire;
    EditText lowAmount;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        remindPics = findViewById(R.id.remindPicturesToggleButton);
        remindExpire = findViewById(R.id.remindExpireToggleButton);
        remindLow = findViewById(R.id.remindLowToggleButton);
        daysExpire = findViewById(R.id.dayseditText);
        lowAmount = findViewById(R.id.amountLeftEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings(view);
            }
        });
        SharedPreferences shared = this.getSharedPreferences("Settings",MODE_PRIVATE);

        remindPics.setChecked(shared.getBoolean("PicRemind",false));
        remindExpire.setChecked(shared.getBoolean("ExpireRemind",false));
        remindLow.setChecked(shared.getBoolean("LowRemind",false));
        daysExpire.setText(shared.getInt("DaysExpire",0) + "");
        lowAmount.setText(shared.getInt("AmountLeft",0) + "");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings(view);
            }
        });
    }
    public void saveSettings(View view){
        Intent intent = new Intent(this,MainActivity.class);

        boolean picRemind,expireRemind,lowRemind;
        int daysToExpire,amountLow;

        picRemind = remindPics.isChecked();
        expireRemind = remindExpire.isChecked();
        lowRemind = remindLow.isChecked();
        daysToExpire = Integer.parseInt(daysExpire.getText().toString());
        amountLow = Integer.parseInt(lowAmount.getText().toString());

        SharedPreferences shared = this.getSharedPreferences("Settings",MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("PicRemind",picRemind);
        editor.putBoolean("ExpireRemind",expireRemind);
        editor.putBoolean("LowRemind",lowRemind);
        editor.putInt("DaysExpire",daysToExpire);
        editor.putInt("AmountLeft",amountLow);
        editor.apply();//commit(immediate) v. apply(background)

        Context context = getApplicationContext();
        CharSequence text = "Saved";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        startActivity(intent);
    }
}
