package com.example.cocktailtime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailsStrongActivity extends AppCompatActivity {

    TextView name, strength;
    ImageView imageView;
    CocktailResponseStrong cocktailResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_strong);

        name = findViewById(R.id.naam);
        strength = findViewById(R.id.sterkte);
        imageView = findViewById(R.id.imageView);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            cocktailResponse = (CocktailResponseStrong) intent.getSerializableExtra("data");

            String cocktailname = cocktailResponse.getNaam();
            int cocktailstrength = cocktailResponse.getSterkte();

            String image = cocktailResponse.getImage_location();

            Picasso.with(this).load(image).into(imageView);
            name.setText(cocktailname);
            strength.setText("Strength: " + String.valueOf(cocktailstrength) + "%");



        }
    }
}
