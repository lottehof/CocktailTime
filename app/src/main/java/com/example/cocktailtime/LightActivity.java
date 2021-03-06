package com.example.cocktailtime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LightActivity extends AppCompatActivity implements CocktailLightAdapter.ClickedItem, NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    CocktailLightAdapter cocktailLightadapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");
        cocktailLightadapter = new CocktailLightAdapter(this::ClickedUser);
        getAllCocktailsLight();

        //drawer menu settings
        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true); //enalbe hamburger
        drawerToggle.syncState();

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //In this functions it checks if the response fetch the data succesfully, if so then it is put into cocktailadapter and then put into the recyclerview.
    public void getAllCocktailsLight(){
        Call<List<CocktailResponseLight>> cocktaillist = ApiClient.getLightAlcoholicService().getAllCocktailsLight();
        cocktaillist.enqueue((new Callback<List<CocktailResponseLight>>() {
            @Override
            public void onResponse(Call<List<CocktailResponseLight>> call, Response<List<CocktailResponseLight>> response) {
                if(response.isSuccessful()){
                    List<CocktailResponseLight> cocktailResponses = response.body();
                    cocktailLightadapter.setData(cocktailResponses);
                    recyclerView.setAdapter(cocktailLightadapter);

                    //Saves the api in a file (stores it locally)
                    try {
                        FileOutputStream fos = openFileOutput("CocktailLig.dat", Context.MODE_PRIVATE);
                        ObjectOutputStream os = new ObjectOutputStream(fos);
                        os.writeObject(cocktailResponses);
                        os.close();
                        fos.close();

                      //  Toast.makeText(getApplicationContext(), "Text saved" + getFilesDir() + "/" + "CocktailLig.dat", Toast.LENGTH_LONG).show();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Menu menuNav = navigationView.getMenu();
                    MenuItem nav_addCocktail = menuNav.findItem(R.id.add);
                    nav_addCocktail.setEnabled(true);
                }
            }
            //If system fails to get a response from api, than it will get its data from the file
            @Override
            public void onFailure(Call<List<CocktailResponseLight>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                List<CocktailResponseLight> cocktailResponses = null;
                //If system fails to get a response from api, than it will get its data from the file
                try {
                    FileInputStream fis = openFileInput("CocktailLig.dat");
                    ObjectInputStream is = new ObjectInputStream(fis);

                    cocktailResponses = (List<CocktailResponseLight>) is.readObject();
                    is.close();
                    fis.close();
                    cocktailLightadapter.setData(cocktailResponses);
                    recyclerView.setAdapter(cocktailLightadapter);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //disables add cocktail button because you're offline.
                Menu menuNav = navigationView.getMenu();
                MenuItem nav_addCocktail = menuNav.findItem(R.id.add);
                nav_addCocktail.setEnabled(false);
            }
        }));
    }

    //To detailpage
    @Override
    public void ClickedUser(CocktailResponseLight cocktailResponse) {
        startActivity(new Intent(this, DetailsLightActivity.class).putExtra("data", cocktailResponse));
    }

    //menu + searchbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflatermenu = getMenuInflater();
        inflatermenu.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cocktailLightadapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    //Menu stuff
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.home:
                Intent home = new Intent(LightActivity.this, SecondActivity.class);
                startActivity(home);
               // Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.non:
                Intent non = new Intent(LightActivity.this, NonActivity.class);
                startActivity(non);
               // Toast.makeText(this, "Non Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.light:
                Intent light = new Intent(LightActivity.this, LightActivity.class);
                startActivity(light);
               // Toast.makeText(this, "Light Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.medium:
                Intent medium = new Intent(LightActivity.this, MediumActivity.class);
                startActivity(medium);
               // Toast.makeText(this, "Medium Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.strong:
                Intent strong = new Intent(LightActivity.this, StrongActivity.class);
                startActivity(strong);
               // Toast.makeText(this, "Strong Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent add = new Intent(LightActivity.this, CocktailAddActivity.class);
                startActivity(add);
                break;
        }
        return false;
    }

}
