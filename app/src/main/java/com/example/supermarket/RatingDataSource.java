package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RatingDataSource {

    private SQLiteDatabase database;
    private RatingDBHelper dbHelper;

    public RatingDataSource(Context context){
        dbHelper = new RatingDBHelper(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public int insertRestaurant(Restaurant r){
        int restaurantId = -1;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("name", r.getName());
            initialValues.put("address", r.getAddress());

            long rowId = database.insert("restaurant", null, initialValues);
            if (rowId > 0) {
                restaurantId = (int) rowId;
                r.setRestaurantId(restaurantId);
                Restaurant.setLastInsertedRestaurantId(restaurantId);
            }
        } catch (Exception e) {
            Log.e("DB ERROR", "ERROR INSERTING RESTAURANT", e);
        }
        return restaurantId;
    }

    public boolean insertRating(int restaurantId, Rating r) {
        boolean didSucceed = false;
        try {
             restaurantId = Restaurant.getLastInsertedRestaurantId();
            if (restaurantId == -1) {
                Log.e("DB ERROR", "Cannot insert rating: No valid restaurant ID");
                return false;
            }

            ContentValues initialValues = new ContentValues();
            initialValues.put("restaurant_id", restaurantId);
            initialValues.put("liquor_rating", r.getLiquorRating());
            initialValues.put("produce_rating", r.getProduceRating());
            initialValues.put("meat_rating", r.getMeatRating());
            initialValues.put("cheese_rating", r.getCheeseRating());
            initialValues.put("checkout_rating", r.getCheckoutRating());

            long rowId = database.insert("rating", null, initialValues);
            if (rowId > 0) {
                r.setRatingId((int) rowId);
                didSucceed = true;
            }
        } catch (Exception e) {
            Log.e("DB ERROR", "ERROR INSERTING RATING", e);
        }
        return didSucceed;
    }





    }





