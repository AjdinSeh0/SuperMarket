package com.example.supermarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class RatingDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ratings.db";
    private static final int DATABASE_VERSION = 1;

    // Table for Restaurants (Stores restaurant name and address)
    private static final String CREATE_TABLE_RESTAURANT =
            "CREATE TABLE restaurant (" +
                    "restaurant_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "address TEXT NOT NULL);";

    // Table for Ratings (Stores ratings for different categories)
    private static final String CREATE_TABLE_RATING =
            "CREATE TABLE rating (" +
                    "rating_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "restaurant_id INTEGER NOT NULL, " +  // Foreign key linking to restaurant
                    "liquor_rating REAL, " +
                    "produce_rating REAL, " +
                    "meat_rating REAL, " +
                    "cheese_rating REAL, " +
                    "checkout_rating REAL, " +
                    "FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id));";

public RatingDBHelper(Context context){
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
}


    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE_RESTAURANT);
    db.execSQL(CREATE_TABLE_RATING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RatingDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS rating");
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(db);
    }
}
