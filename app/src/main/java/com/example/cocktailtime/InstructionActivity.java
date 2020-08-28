package com.example.cocktailtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstructionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextInputEditText instructie;
    Button reg_instructie;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        createNotificationChannel();

        instructie = findViewById(R.id.instructie);
        reg_instructie = findViewById(R.id.btn_instructie);

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



        reg_instructie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInstruction(createRequest());
                openNextScreen();
//                Toast.makeText(InstructionActivity.this,"Reminder Set!",Toast.LENGTH_SHORT).show();

                //Set Notification Channel and set time when reminder needs to been show
                Intent intent = new Intent(InstructionActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(InstructionActivity.this, 100, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeAtButtonClick = System.currentTimeMillis();

                long tenSecondsInMillis = 100 * 10;

                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        timeAtButtonClick + tenSecondsInMillis,
                        pendingIntent);


            }
        });
    }
    //Create channel for the notification
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CocktailToegevoegdChannel";
            String description = "Channel for Cocktail Toegevoegd";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyCocktail", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void openNextScreen() {
        Intent intent = new Intent(this, SecondActivity.class);
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
//                    Toast.makeText(InstructionActivity.this,"Saved successfully",Toast.LENGTH_LONG).show();
                }else{
//                    Toast.makeText(InstructionActivity.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<InstructionResponse> call, Throwable t) {
                Toast.makeText(InstructionActivity.this,"Instruction has been added to the cocktail.",Toast.LENGTH_LONG).show();
            }
        });
    }

    //Menu stuff

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.home:
                Intent home = new Intent(InstructionActivity.this, SecondActivity.class);
                startActivity(home);
//                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.non:
                Intent non = new Intent(InstructionActivity.this, NonActivity.class);
                startActivity(non);
//                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.light:
                Intent light = new Intent(InstructionActivity.this, LightActivity.class);
                startActivity(light);
//                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.medium:
                Intent medium = new Intent(InstructionActivity.this, MediumActivity.class);
                startActivity(medium);
//                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.strong:
                Intent strong = new Intent(InstructionActivity.this, StrongActivity.class);
                startActivity(strong);
//                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent add = new Intent(InstructionActivity.this, CocktailAddActivity.class);
                startActivity(add);
                break;
        }
        return false;
    }
}