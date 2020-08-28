package com.example.cocktailtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextInputEditText ingredient;
    Button reg_ingredient, reg_benodigheden;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        ingredient = findViewById(R.id.ingredient);
        reg_ingredient = findViewById(R.id.btn_ingredient);
        reg_benodigheden = findViewById(R.id.btn_benodigheden);

        toolbar = findViewById(R.id.toolbar);


        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        //drawer menu settings
        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true); //enalbe hamburger
        drawerToggle.syncState();

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);


        reg_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveIngredient(createRequest());
            }
        });

        reg_benodigheden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextScreen();
            }
        });
    }

    public void openNextScreen() {
        Intent intent = new Intent(this, EquipmentsActivity.class);
        startActivity(intent);
    }


    public IngredientRequest createRequest(){
        IngredientRequest ingredientRequest = new IngredientRequest();
        ingredientRequest.setIngredient(ingredient.getText().toString());

        return ingredientRequest;
    }



    public void saveIngredient(IngredientRequest ingredientRequest){

        Call<IngredientResponse> ingredientResponeCall = ApiClient.getIngredientService().saveIngredient(ingredientRequest);

        ingredientResponeCall.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(IngredientActivity.this,"Saved successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(IngredientActivity.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                Toast.makeText(IngredientActivity.this,"Ingredient has been added to cocktail, you can add an another ingredient if you want",Toast.LENGTH_LONG).show();
            }
        });
    }

    //Menu stuff

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.home:
                Intent home = new Intent(IngredientActivity.this, SecondActivity.class);
                startActivity(home);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.non:
                Intent non = new Intent(IngredientActivity.this, NonActivity.class);
                startActivity(non);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.light:
                Intent light = new Intent(IngredientActivity.this, LightActivity.class);
                startActivity(light);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.medium:
                Intent medium = new Intent(IngredientActivity.this, MediumActivity.class);
                startActivity(medium);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.strong:
                Intent strong = new Intent(IngredientActivity.this, StrongActivity.class);
                startActivity(strong);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent add = new Intent(IngredientActivity.this, CocktailAddActivity.class);
                startActivity(add);
                break;
        }
        return false;
    }
}