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

public class IngredientActivity extends AppCompatActivity {

    TextInputEditText ingredient;
    Button reg_ingredient, reg_benodigheden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        ingredient = findViewById(R.id.ingredient);
        reg_ingredient = findViewById(R.id.btn_ingredient);
        reg_benodigheden = findViewById(R.id.btn_benodigheden);


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
                Toast.makeText(IngredientActivity.this,"Request failed "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}