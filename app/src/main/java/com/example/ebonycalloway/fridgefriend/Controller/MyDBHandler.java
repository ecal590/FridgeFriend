package com.example.ebonycalloway.fridgefriend.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ebonycalloway.fridgefriend.POJO.Food;

/*
    Created by ebonycalloway on 4/19/17.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "fridgeDB.db";
    private static final String TABLE_FOOD = "food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FOODNAME = "foodname";
    private static final String COLUMN_FOODDESCRIPTION = "fooddescription";
    private static final String COLUMN_FOODAMOUNT = "foodamount";
    private static final String COLUMN_FOODPRICE = "foodprice";
    private static final String COLUMN_FOODEXPIRATION = "foodexpiration";
    private static final String COLUMN_FOODRATING = "foodrating";
    private static final String COLUMN_FOODHEALTH = "foodhealth";
    private static final String COLUMN_FOODSHOPPING = "foodshopping";
    private static final String COLUMN_FOODFRIDGE = "foodfridge";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_FOOD + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT, " +
                COLUMN_FOODDESCRIPTION + " TEXT, " +
                COLUMN_FOODAMOUNT + " TEXT, " +
                COLUMN_FOODPRICE + " TEXT, " +
                COLUMN_FOODEXPIRATION + " TEXT, " +
                COLUMN_FOODRATING + " TEXT, " +
                COLUMN_FOODHEALTH+ " TEXT, " +
                COLUMN_FOODSHOPPING+ " TEXT, " +
                COLUMN_FOODFRIDGE + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        onCreate(sqLiteDatabase);
    }

    //Add/update a new row in the table
    public void addFood(Food food){
        if(selectFood(food.getName(),food.getDescription()).equals("")) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FOODNAME, food.getName());
            values.put(COLUMN_FOODDESCRIPTION, food.getDescription());
            values.put(COLUMN_FOODAMOUNT, food.getAmountLeft());
            values.put(COLUMN_FOODPRICE, food.getPrice());
            values.put(COLUMN_FOODEXPIRATION, food.getExpiration());
            values.put(COLUMN_FOODRATING, food.getRating());
            values.put(COLUMN_FOODHEALTH, food.getHealthGroup());
            values.put(COLUMN_FOODSHOPPING, food.isShoppingList());
            values.put(COLUMN_FOODFRIDGE, food.isFridgeList());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_FOOD, null, values);
            db.close();
        }
        else{
            ContentValues values = new ContentValues();
            values.put(COLUMN_FOODNAME, food.getName());
            values.put(COLUMN_FOODDESCRIPTION, food.getDescription());
            values.put(COLUMN_FOODAMOUNT, food.getAmountLeft());
            values.put(COLUMN_FOODPRICE, food.getPrice());
            values.put(COLUMN_FOODEXPIRATION, food.getExpiration());
            values.put(COLUMN_FOODRATING, food.getRating());
            values.put(COLUMN_FOODHEALTH, food.getHealthGroup());
            values.put(COLUMN_FOODSHOPPING, food.isShoppingList());
            values.put(COLUMN_FOODFRIDGE, food.isFridgeList());
            SQLiteDatabase db = getWritableDatabase();
            String tempString = "foodname =\"" + food.getName()+ "\" AND " + "fooddescription =\"" + food.getDescription() +"\"";
            db.update(TABLE_FOOD, values, tempString,null);
            db.close();
        }
    }
    //Delete food from the database
    public void deleteFood(String foodName, String description){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODNAME + "=\"" + foodName + "\"" + " AND " + COLUMN_FOODDESCRIPTION + "=\"" +  description + "\";");
    }

    //Select all food from the database
    public String selectFood(String foodName, String description){
        SQLiteDatabase db = getWritableDatabase();
        String dbString = "";
        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODNAME + "=\"" + foodName + "\"" + " AND " + COLUMN_FOODDESCRIPTION + "=\"" +  description + "\";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("foodname")) != null) {
                dbString += c.getString(c.getColumnIndex("foodname"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("fooddescription"));
                dbString += ",";
                dbString += c.getInt(c.getColumnIndex("foodamount"));
                dbString += ",";
                dbString += c.getDouble(c.getColumnIndex("foodprice"));
                dbString += ",";
                dbString += c.getLong(c.getColumnIndex("foodexpiration"));
                dbString += ",";
                dbString += c.getDouble(c.getColumnIndex("foodrating"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("foodhealth"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("foodshopping"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("foodfridge"));
                dbString += ";";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;

    }


    public String fridgeToString(String order){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query;
        query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODFRIDGE + "=\"" + "true"  +"\""+ " ORDER BY " + COLUMN_FOODEXPIRATION;//TODO:Want to order?

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("foodname")) != null) {
                dbString += c.getString(c.getColumnIndex("foodname"));
                dbString += ":";
                dbString += c.getString(c.getColumnIndex("fooddescription"));
                dbString += ";";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;
    }
    public String fullInfo(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("foodname")) != null) {
                dbString += c.getString(c.getColumnIndex("foodname"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("fooddescription"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("fooddescription"));
                dbString += ",";
                dbString += c.getInt(c.getColumnIndex("foodamount"));
                dbString += ",";
                dbString += c.getDouble(c.getColumnIndex("foodprice"));
                dbString += ",";
                dbString += c.getLong(c.getColumnIndex("foodexpiration"));
                dbString += ",";
                dbString += c.getDouble(c.getColumnIndex("foodrating"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("foodhealth"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("foodshopping"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("foodfridge"));
                dbString += ";";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;

    }

    public String shoppingListToString(String order){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query;
        if(order.equals("alpha")) {
            query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODSHOPPING + "=\"" + "true"  +"\""+ " ORDER BY " + COLUMN_FOODNAME;
        }else if(order.equals("group")){
            query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODSHOPPING + "=\"" + "true"  +"\""+ " ORDER BY " + COLUMN_FOODHEALTH;
        }else if(order.equals("rating")){
            query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODSHOPPING + "=\"" + "true"  +"\""+ " ORDER BY " + COLUMN_FOODRATING;
        }else{
            query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODSHOPPING + "=\"" + "true"  +"\""+ " ORDER BY " + COLUMN_FOODEXPIRATION;
        }
        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("foodname")) != null && c.getString(c.getColumnIndex("foodshopping")).equals("true")) {
                dbString += c.getString(c.getColumnIndex("foodname"));
                dbString += ":";
                dbString += c.getString(c.getColumnIndex("fooddescription"));
                dbString += ";";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;
    }
}
