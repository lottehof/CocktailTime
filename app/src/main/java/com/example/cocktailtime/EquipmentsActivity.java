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

public class EquipmentsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextInputEditText benodigheid;
    Button reg_benodigheid, reg_instructie;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);

        benodigheid = findViewById(R.id.benodigheid);
        reg_benodigheid = findViewById(R.id.btn_benodigheid);
        reg_instructie = findViewById(R.id.btn_instructies);

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


        reg_benodigheid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEquipment(createRequest());
            }
        });

        reg_instructie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextScreen();
            }
        });
    }

    public void openNextScreen() {
        Intent intent = new Intent(this, InstructionActivity.class);
        startActivity(intent);
    }


    public EquipmentsRequest createRequest(){
        EquipmentsRequest equipmentsRequest = new EquipmentsRequest();
        equipmentsRequest.setBenodigheid(benodigheid.getText().toString());

        return equipmentsRequest;
    }



    public void saveEquipment(EquipmentsRequest equipmentsRequest){

        Call<EquipmentsResponse> equipmentsResponseCall = ApiClient.getEquipmentService().saveEquipment(equipmentsRequest);

        equipmentsResponseCall.enqueue(new Callback<EquipmentsResponse>() {
            @Override
            public void onResponse(Call<EquipmentsResponse> call, Response<EquipmentsResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(EquipmentsActivity.this,"Saved successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(EquipmentsActivity.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<EquipmentsResponse> call, Throwable t) {
                Toast.makeText(EquipmentsActivity.this,"Request failed "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    //Menu stuff

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.home:
                Intent home = new Intent(EquipmentsActivity.this, SecondActivity.class);
                startActivity(home);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.non:
                Intent non = new Intent(EquipmentsActivity.this, NonActivity.class);
                startActivity(non);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.light:
                Intent light = new Intent(EquipmentsActivity.this, LightActivity.class);
                startActivity(light);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.medium:
                Intent medium = new Intent(EquipmentsActivity.this, MediumActivity.class);
                startActivity(medium);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.strong:
                Intent strong = new Intent(EquipmentsActivity.this, StrongActivity.class);
                startActivity(strong);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent add = new Intent(EquipmentsActivity.this, CocktailAddActivity.class);
                startActivity(add);
                break;
        }
        return false;
    }
}