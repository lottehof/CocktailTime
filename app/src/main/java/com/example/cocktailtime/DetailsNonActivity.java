package com.example.cocktailtime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DetailsNonActivity extends AppCompatActivity {


    TextView name, strength, ingredient, benodigheid, instructie;
    ImageView imageView;
    CocktailResponseNon cocktailResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_non);

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
        if (intent.getExtras() != null) {
            cocktailResponse = (CocktailResponseNon) intent.getSerializableExtra("data");


            String cocktailname = cocktailResponse.getNaam();
            int cocktailstrength = cocktailResponse.getSterkte();
            String image = cocktailResponse.getImage_location();

            ArrayList ingredienten = (ArrayList) cocktailResponse.getIngredienten();
            ArrayList benodigheden = (ArrayList) cocktailResponse.getBenodigheden();
          ArrayList instructies = (ArrayList) cocktailResponse.getInstructies();


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
