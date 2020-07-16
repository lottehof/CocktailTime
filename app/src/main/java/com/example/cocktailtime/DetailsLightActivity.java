package com.example.cocktailtime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailsLightActivity extends AppCompatActivity {

    TextView name, strength;
    ImageView imageView;
    CocktailResponseLight cocktailResponseLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_light);

        name = findViewById(R.id.naam);
        strength = findViewById(R.id.sterkte);
        imageView = findViewById(R.id.imageView);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            cocktailResponseLight = (CocktailResponseLight) intent.getSerializableExtra("data");

            String cocktailname = cocktailResponseLight.getNaam();
            int cocktailstrength = cocktailResponseLight.getSterkte();
            String image = cocktailResponseLight.getImage_location();

            Picasso.with(this).load(image).into(imageView);

            name.setText(cocktailname);
            strength.setText("Strength: " + String.valueOf(cocktailstrength) + "%");



        }
    }
}
