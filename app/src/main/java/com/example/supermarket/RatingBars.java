package com.example.supermarket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class RatingBars extends AppCompatActivity {
// Hashmap that stores the ratingValues with the Int being the ratingId and the
// float being the actual rating value
    private final HashMap<Integer, Float> ratingValues = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rate_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initReturnButton();
        initRateButton();
// calls the ratingChange method for each RatingBar
        ratingChange(findViewById(R.id.ratingBarLiquor));
        ratingChange(findViewById(R.id.ratingBarCheckout));
        ratingChange(findViewById(R.id.ratingBarMeat));
        ratingChange(findViewById(R.id.ratingBarProduce));
        ratingChange(findViewById(R.id.ratingBarCheese));
    }
// this just goes back to the main page
    private void initReturnButton(){
        Button button = findViewById(R.id.buttonReturn);
        button.setOnClickListener(v ->{
            Intent intent = new Intent(RatingBars.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
//this sets a change listener to a ratingbar then sets the ratingBar Id and
// and value into the hashmap
    private void ratingChange(RatingBar ratingBar) {
        ratingBar.setOnRatingBarChangeListener((ratingBar1, v, fromUser) -> {
            ratingValues.put(ratingBar1.getId(), v);
            Log.d("RatingBar", "Updated rating for ID " + ratingBar1.getId() + ": " + v);
        });
    }


// does bulk of the work
    private void initRateButton(){
        Button button = findViewById(R.id.buttonRateRestaurant);
// when the "rate" button is clicked it gets the value of each ratingBar and
// assigns a value of 0 by default
        button.setOnClickListener(v ->{
            float liquorRating = ratingValues.getOrDefault(R.id.ratingBarLiquor, 0.0f);
            float produceRating = ratingValues.getOrDefault(R.id.ratingBarProduce, 0.0f);
            float meatRating = ratingValues.getOrDefault(R.id.ratingBarMeat, 0.0f);
            float cheeseRating = ratingValues.getOrDefault(R.id.ratingBarCheese, 0.0f);
            float checkoutRating = ratingValues.getOrDefault(R.id.ratingBarCheckout, 0.0f);
// this is the average text that appears
            TextView averageText = findViewById(R.id.editAverage);
// calculates the average between the bars and sets it to the text
            float average = (liquorRating + produceRating + meatRating + checkoutRating + cheeseRating) / 5;
            String averagetxt = average + "";
            averageText.setText(averagetxt);
// creates a RatingDataSource object for all the methods
            RatingDataSource ds = new RatingDataSource(this);
            ds.open();
// creates a Rating method to get its methods and set its variables
            Rating rating = new Rating();
// This finds the last inserted Restaurant Id from the
// static method in the Restaurant class
            int restaurantId = Restaurant.getLastInsertedRestaurantId();
// if the id is -1 then basically something went wrong and closes the db
            if(restaurantId == -1){
                Log.e("DB ERROR", "no valid restaurant ID");
                ds.close();
                return;
            }
// this sets the Rating object variables to the ratingBar values
            rating.setLiquorRating(liquorRating);
            rating.setProduceRating(produceRating);
            rating.setMeatRating(meatRating);
            rating.setCheeseRating(cheeseRating);
            rating.setCheckoutRating(checkoutRating);
// retunrs a boolean if the insert worked, passes the restaurantId
// and the rating variable
            boolean success = ds.insertRating(restaurantId, rating);
            if(success){
                Log.d("YESS", "it worked");
            }
            else{
                Log.d("NOOO", "It didnt work");
            }

            ds.close();

        });

    }


}
