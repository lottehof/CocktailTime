package com.example.cocktailtime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DetailsLightActivity extends AppCompatActivity {

    TextView name, strength, ingredient, benodigheid, instructie;
    ImageView imageView;
    CocktailResponseLight cocktailResponseLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_light);

        name = findViewById(R.id.naam);
        strength = findViewById(R.id.sterkte);
        imageView = findViewById(R.id.imageView);
        ingredient = findViewById(R.id.ingredient);
        benodigheid = findViewById(R.id.benodigheden);
        instructie = findViewById(R.id.instructies);



        String total = "";
        String benodighedentotal = "";
        String instructiestotal = "";


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            cocktailResponseLight = (CocktailResponseLight) intent.getSerializableExtra("data");

            String cocktailname = cocktailResponseLight.getNaam();
            int cocktailstrength = cocktailResponseLight.getSterkte();
            String image = cocktailResponseLight.getImage_location();


            ArrayList ingredienten = (ArrayList) cocktailResponseLight.getIngredienten();
            ArrayList benodigheden = (ArrayList) cocktailResponseLight.getBenodigheden();
            ArrayList instructies = (ArrayList) cocktailResponseLight.getInstructies();


            for (int i = 0; i < ingredienten.size(); i++) {
                LinkedHashMap item = (LinkedHashMap) ingredienten.get(i);
                total = total + "\n"  + "\n" + item.get("ingredient").toString();
            }

            for (int i = 0; i < benodigheden.size(); i++)
            {
                LinkedHashMap item = (LinkedHashMap) benodigheden.get(i);
                benodighedentotal = benodighedentotal + "\n"  + "\n" + item.get("benodigheid").toString();
            }

            for (int i = 0; i < instructies.size(); i++)
            {
                LinkedHashMap item = (LinkedHashMap) instructies.get(i);
                instructiestotal = instructiestotal + "\n" + "\n" + item.get("instructie").toString();
            }
            Picasso.with(this).load(image).into(imageView);

            name.setText(cocktailname);
            strength.setText(String.valueOf(cocktailstrength) + "%");
            ingredient.setText(total);
            benodigheid.setText(benodighedentotal);
            instructie.setText(instructiestotal);



        }
    }
}
