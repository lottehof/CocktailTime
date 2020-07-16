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

public class InstructionActivity extends AppCompatActivity {

    TextInputEditText instructie;
    Button reg_instructie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        instructie = findViewById(R.id.instructie);
        reg_instructie = findViewById(R.id.btn_instructie);


        reg_instructie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInstruction(createRequest());
                openNextScreen();
            }
        });

    }

    public void openNextScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public InstructionRequest createRequest(){
        InstructionRequest instructionRequest = new InstructionRequest();
        instructionRequest.setInstructie(instructie.getText().toString());

        return instructionRequest;
    }



    public void saveInstruction(InstructionRequest instructionRequest){

        Call<InstructionResponse> instructionResponseCall = ApiClient.getInstructionsService().saveInstruction(instructionRequest);

        instructionResponseCall.enqueue(new Callback<InstructionResponse>() {
            @Override
            public void onResponse(Call<InstructionResponse> call, Response<InstructionResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(InstructionActivity.this,"Saved successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(InstructionActivity.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<InstructionResponse> call, Throwable t) {
                Toast.makeText(InstructionActivity.this,"Request failed "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}