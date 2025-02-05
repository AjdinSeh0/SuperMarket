package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initRatingButton();
    }

    private void initRatingButton(){

// sets a listener to the rating button
        Button button = findViewById(R.id.buttonRate);
        button.setOnClickListener(v -> {
// gets the values for the name and address
            EditText name = findViewById(R.id.editName);
            EditText address = findViewById(R.id.editAddress);
// checks to see if they are empty
            if(!name.getText().toString().trim().isEmpty() && !address.getText().toString().trim().isEmpty()){
                RatingDataSource ds = new RatingDataSource(this);
                ds.open();
// if they're good it creates a new Restaurant object and sets the
// values to the name and address fields
                Restaurant restaurant = new Restaurant();
                restaurant.setName(name.getText().toString());
                restaurant.setAddress(name.getText().toString());
// sets the restaurantId to the database, if it returns greater than 0,
// that means that the name/address have been sucessfully inserted
                int restaurantId = ds.insertRestaurant(restaurant);

                if (restaurantId > 0){
                    Log.d("WORKED!", "Restaurant inserted with id " + restaurantId);
                }
                else{
                    Log.e("FAILED", "failed to insert restaurant");
                }
                ds.close();
    // once its done processing the sql it closes and switches to the other scene
                Intent intent = new Intent(MainActivity.this, RatingBars.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);}
            else{
                Log.d("Empty inserts!", "Empty inserts");
                Toast.makeText(MainActivity.this, "Make sure both fields have values", Toast.LENGTH_SHORT).show();
            }



            });
        }
    }