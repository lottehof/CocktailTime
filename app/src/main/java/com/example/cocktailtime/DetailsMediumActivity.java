package com.example.cocktailtime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailsMediumActivity extends AppCompatActivity {

    TextView name, strength;
    ImageView imageView;
    CocktailResponseMedium cocktailResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_medium);

        name = findViewById(R.id.naam);
        strength = findViewById(R.id.sterkte);
        imageView = findViewById(R.id.imageView);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            cocktailResponse = (CocktailResponseMedium) intent.getSerializableExtra("data");

            String cocktailname = cocktailResponse.getNaam();
            int cocktailstrength = cocktailResponse.getSterkte();
            String image = cocktailResponse.getImage_location();

            Picasso.with(this).load(image).into(imageView);

            name.setText(cocktailname);
            strength.setText("Strength: " + String.valueOf(cocktailstrength) + "%");



        }
    }
}

