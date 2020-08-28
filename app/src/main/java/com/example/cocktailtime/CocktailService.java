package com.example.cocktailtime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CocktailService {
    //All get requests

    //All cocktails
    @GET("cocktails")
    Call<List<CocktailResponse>> getAllCocktails();

    //Non-alcoholic cocktails
    @GET("cocktail/strength/non-alcoholic")
    Call<List<CocktailResponseNon>> getAllCocktailsNon();

    //Cocktails with strength between 1 & 10
    @GET("cocktail/strength/light")
    Call<List<CocktailResponseLight>> getAllCocktailsLight();

    //Cocktails between 11 - 20
    @GET("cocktail/strength/medium")
    Call<List<CocktailResponseMedium>> getAllCocktailsMedium();

    //Cocktails higher than 20
    @GET("cocktail/strength/strong")
    Call<List<CocktailResponseStrong>> getAllCocktailsStrong();

    //Post first part of the Cocktail Name, image_location etc.
    @POST("create")
    Call<CocktailAddResponse> saveCocktail(@Body CocktailRequest cocktailRequest);

    //Post ingredients to the new cocktail you created
    @POST("createingredient")
    Call<IngredientResponse> saveIngredient(@Body IngredientRequest userRequest);

    @POST("createbenodigheid")
    Call<EquipmentsResponse> saveEquipment(@Body EquipmentsRequest userRequest);

    @POST("createinstructies")
    Call<InstructionResponse> saveInstruction(@Body InstructionRequest userRequest);



}
