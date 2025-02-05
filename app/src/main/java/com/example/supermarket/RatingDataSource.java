package com.example.supermarket;
// hanldes the bulk of the methods for the Data Base
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
        // requires a Restaurant object and sets it by default to -1
        int restaurantId = -1;
        try {
    // uses ContentValues to place future values into the database using key, value pairs

            ContentValues initialValues = new ContentValues();
            initialValues.put("name", r.getName());
            initialValues.put("address", r.getAddress());
    // gets the rowId for the insertRestaurant object and makes sure its valid
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
            //gets the lastInsertedRestaurant Id from the Restaurant method
            //using this as a foreign key for the Restaurant table
             restaurantId = Restaurant.getLastInsertedRestaurantId();
            if (restaurantId == -1) {
                Log.e("DB ERROR", "Cannot insert rating: No valid restaurant ID");
                return false;
            }
    // Basically the same thing as the other one uses keyvalue pairs to insert the information
            ContentValues initialValues = new ContentValues();
            initialValues.put("restaurant_id", restaurantId);
            initialValues.put("liquor_rating", r.getLiquorRating());
            initialValues.put("produce_rating", r.getProduceRating());
            initialValues.put("meat_rating", r.getMeatRating());
            initialValues.put("cheese_rating", r.getCheeseRating());
            initialValues.put("checkout_rating", r.getCheckoutRating());
    // again checks rowId for accuracy
            long rowId = database.insert("rating", null, initialValues);
            if (rowId > 0) {
                r.setRatingId((int) rowId);
                didSucceed = true;
            }
        } catch (Exception e) {
            Log.e("DB ERROR", "ERROR INSERTING RATING", e);
        }
        //returns true if it works
        return didSucceed;
    }





    }





