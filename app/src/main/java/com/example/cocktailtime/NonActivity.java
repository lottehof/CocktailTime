package com.example.cocktailtime;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NonActivity extends AppCompatActivity implements CocktailNonAdapter.ClickedItem, NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    RecyclerView recyclerView;
    CocktailNonAdapter cocktailNonadapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        cocktailNonadapter = new CocktailNonAdapter(this::ClickedUser);

        getAllCocktailsNon();

        //drawer menu settings
        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true); //enalbe hamburger
        drawerToggle.syncState();

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void getAllCocktailsNon(){
        Call<List<CocktailResponseNon>> cocktaillist = ApiClient.getNonAlcoholicService().getAllCocktailsNon();

        cocktaillist.enqueue((new Callback<List<CocktailResponseNon>>() {
            @Override
            public void onResponse(Call<List<CocktailResponseNon>> call, Response<List<CocktailResponseNon>> response) {
                if(response.isSuccessful()){
                    List<CocktailResponseNon> cocktailResponses = response.body();
                    cocktailNonadapter.setData(cocktailResponses);
                    recyclerView.setAdapter(cocktailNonadapter);
                }
            }
            @Override
            public void onFailure(Call<List<CocktailResponseNon>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());


            }
        }));
    }

    //To detailpage
    @Override
    public void ClickedUser(CocktailResponseNon cocktailResponse) {
        startActivity(new Intent(this, DetailsNonActivity.class).putExtra("data", cocktailResponse));
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
                cocktailNonadapter.getFilter().filter(newText);
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
                Intent home = new Intent(NonActivity.this, SecondActivity.class);
                startActivity(home);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.non:
                Intent non = new Intent(NonActivity.this, NonActivity.class);
                startActivity(non);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.light:
                Intent light = new Intent(NonActivity.this, LightActivity.class);
                startActivity(light);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.medium:
                Intent medium = new Intent(NonActivity.this, MediumActivity.class);
                startActivity(medium);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.strong:
                Intent strong = new Intent(NonActivity.this, StrongActivity.class);
                startActivity(strong);
                Toast.makeText(this, "Home Btn Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent add = new Intent(NonActivity.this, CocktailAddActivity.class);
                startActivity(add);
                break;

        }
        return false;
    }
}
