package com.example.cocktailtime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsNonActivity extends AppCompatActivity {


    TextView name, strength;
    ImageView image_location;
    CocktailResponseNon cocktailResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_non);

        name = findViewById(R.id.naam);
        strength = findViewById(R.id.sterkte);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            cocktailResponse = (CocktailResponseNon) intent.getSerializableExtra("data");

            String cocktailname = cocktailResponse.getNaam();
            int cocktailstrength = cocktailResponse.getSterkte();
            String userjoined = cocktailResponse.getImage_location();

            name.setText(cocktailname);
            strength.setText("Strength: " + String.valueOf(cocktailstrength) + "%");



        }
    }
}
