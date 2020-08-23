package com.example.cocktailtime;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;

public class CocktailResponseNon implements Serializable {
    private int id;
    private String naam;
    private int sterkte;
    private String image_location;
    private Object ingredienten;
    private Object benodigheden;
    private Object instructies;

    public Object getInstructies() {
        return instructies;
    }

    public Object getBenodigheden() {
        return benodigheden;
    }

    public Object getIngredienten() {
        return ingredienten;
    }

    public int getSterkte() {
        return sterkte;
    }

    public void setSterkte(int sterkte) {
        this.sterkte = sterkte;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_location() {
        return image_location;
    }

    public void setImage_location(String image_location) {
        this.image_location = image_location;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                "image_location" + image_location +
                ", name'" + naam + '\'' +
                ", strength='" + sterkte + '\'' +
                '}';
    }


}
