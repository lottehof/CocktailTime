package com.example.cocktailtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CocktailAddActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    TextInputEditText naam,sterkte;
    Button reg_user;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    private ImageView imageView;
    private Button btnSelect;
    private int IMG_REQUEST = 21;

    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_add);

        toolbar = findViewById(R.id.toolbar);


        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");


        naam = findViewById(R.id.naam);
        sterkte = findViewById(R.id.sterkte);
        reg_user = findViewById(R.id.btn_register);
        imageView = findViewById(R.id.imageView);
        btnSelect = findViewById(R.id.btnSelectImage);

        //drawer menu settings
        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true); //enalbe hamburger
        drawerToggle.syncState();

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMG_REQUEST);
            }
        });


        reg_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCocktail(createRequest());
//                uploadImage();
                openNextScreen();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

//    private void uploadImage() {
//
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
//        byte [] imageInByte = byteArrayOutputStream.toByteArray();
//
//        String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
//
//        Toast.makeText(this, encodedImage, Toast.LENGTH_SHORT).show();
//
//    }

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
                Toast.makeText(CocktailAddActivity.this,"Cocktail name and strength have been added",Toast.LENGTH_LONG).show();
            }
        });
    }


    //Menu stuff

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.home:
                Intent home = new Intent(CocktailAddActivity.this, SecondActivity.class);
                startActivity(home);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.non:
                Intent non = new Intent(CocktailAddActivity.this, NonActivity.class);
                startActivity(non);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.light:
                Intent light = new Intent(CocktailAddActivity.this, LightActivity.class);
                startActivity(light);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.medium:
                Intent medium = new Intent(CocktailAddActivity.this, MediumActivity.class);
                startActivity(medium);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.strong:
                Intent strong = new Intent(CocktailAddActivity.this, StrongActivity.class);
                startActivity(strong);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent add = new Intent(CocktailAddActivity.this, CocktailAddActivity.class);
                startActivity(add);
                break;
        }
        return false;
    }
}