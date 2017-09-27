package com.example.ebonycalloway.fridgefriend.Controller;

/*
    Created by ebonycalloway on 9/4/17.
*/


import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.MODE_PRIVATE;

public class NotificationService extends IntentService{
    //TODO: Add the notification when food below number, include name of food
    SharedPreferences shared = this.getSharedPreferences("Settings",MODE_PRIVATE);
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
    NotificationCompat.Builder builder;
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try{

            String temp = dbHandler.fullInfo();//TODO: Remind when low or expire
            if(shared.getBoolean("PicRemind",false)){
                builder = new NotificationCompat.Builder(this);
                builder.setContentTitle("Reminder");
                builder.setContentText("Take pictures of your food supply");
                Thread.sleep(604800000);
            }
            if(shared.getBoolean("ExpireRemind",false)){
                int days = shared.getInt("DaysExpire",0);
                builder.setContentTitle("Reminder");
                builder.setContentText(" is going to expire in " + days + " days");
            }
            if(shared.getBoolean("LowRemind",false)){
                int lows = shared.getInt("AmountLeft",0);
                builder.setContentTitle("Reminder");
                builder.setContentText(" is running low, you only have " + lows + " left");
            }

        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public NotificationService(){
        super("ReminderService");
    }

//TODO: Proxy for event listeners
}
