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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView name, email, password, passwordConfirmation;
    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        passwordConfirmation = findViewById(R.id.etConfirmPassword);
        registerButton = findViewById(R.id.btnRegister);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRegister(createRequest());
                openNextScreen();
            }
        });
    }

    public void openNextScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public RegisterRequest createRequest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(name.getText().toString());
        registerRequest.setEmail(email.getText().toString());
        registerRequest.setPassword(password.getText().toString());
        registerRequest.setPasswordConfirmation(passwordConfirmation.getText().toString());

        return registerRequest;
    }



    public void saveRegister(RegisterRequest registerRequest){

        Call<RegisterResponse> registerResponseCall = ApiClient.getRegisterService().saveRegister(registerRequest);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Saved successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Request failed "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
