package com.example.cocktailtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CocktailAddActivity extends AppCompatActivity {

    TextInputEditText naam,sterkte;
    Button reg_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_add);

        naam = findViewById(R.id.naam);
        sterkte = findViewById(R.id.sterkte);
        reg_user = findViewById(R.id.btn_register);


        reg_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCocktail(createRequest());
                openNextScreen();

            }
        });
    }

    public void openNextScreen() {
        Intent intent = new Intent(this, IngredientActivity.class);
        startActivity(intent);
    }


    public CocktailRequest createRequest(){
        CocktailRequest cocktailRequest = new CocktailRequest();
        cocktailRequest.setNaam(naam.getText().toString());
        cocktailRequest.setSterkte(Integer.parseInt(sterkte.getText().toString()));

        return cocktailRequest;
    }



    public void saveCocktail(CocktailRequest cocktailRequest){

        Call<CocktailAddResponse> cocktailAddResponseCall = ApiClient.getCocktailAddService().saveCocktail(cocktailRequest);
        cocktailAddResponseCall.enqueue(new Callback<CocktailAddResponse>() {
            @Override
            public void onResponse(Call<CocktailAddResponse> call, Response<CocktailAddResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(CocktailAddActivity.this,"Saved successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(CocktailAddActivity.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<CocktailAddResponse> call, Throwable t) {
                Toast.makeText(CocktailAddActivity.this,"Request failed "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}