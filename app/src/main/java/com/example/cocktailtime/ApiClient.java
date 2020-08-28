package com.example.cocktailtime;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://iiatimd.manouk.lotte.applepi.nl/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static CocktailService getCocktailService(){
        CocktailService cocktailService = getRetrofit().create(CocktailService.class);
        return cocktailService;
    }

    public static CocktailService getLightAlcoholicService(){
        CocktailService lightAlcoholicService = getRetrofit().create(CocktailService.class);
        return lightAlcoholicService;
    }

    public static CocktailService getNonAlcoholicService(){
        CocktailService nonAlcoholicService = getRetrofit().create(CocktailService.class);
        return nonAlcoholicService;
    }

    public static CocktailService getMediumAlcoholicService(){
        CocktailService mediumAlcoholicService = getRetrofit().create(CocktailService.class);
        return mediumAlcoholicService;
    }

    public static CocktailService getStrongAlcoholicService(){
        CocktailService strongAlcoholicService = getRetrofit().create(CocktailService.class);
        return strongAlcoholicService;
    }

    public static CocktailService getCocktailAddService(){
        CocktailService cocktailAddService = getRetrofit().create(CocktailService.class);
        return cocktailAddService;
    }

    public static CocktailService getIngredientService(){
        CocktailService ingredientService = getRetrofit().create(CocktailService.class);
        return ingredientService;
    }

    public static CocktailService getEquipmentService(){
        CocktailService equipmentService = getRetrofit().create(CocktailService.class);
        return equipmentService;
    }

    public static CocktailService getInstructionsService(){
        CocktailService instructionsService = getRetrofit().create(CocktailService.class);
        return instructionsService;
    }
}
