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

public class EquipmentsActivity extends AppCompatActivity {

    TextInputEditText benodigheid;
    Button reg_benodigheid, reg_instructie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);

        benodigheid = findViewById(R.id.benodigheid);
        reg_benodigheid = findViewById(R.id.btn_benodigheid);
        reg_instructie = findViewById(R.id.btn_instructies);


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
}