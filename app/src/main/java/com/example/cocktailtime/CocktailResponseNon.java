package com.example.cocktailtime;

import java.io.Serializable;

public class CocktailResponseNon implements Serializable {
    private int id;
    private String naam;
    private int sterkte;
    private String image_location;
    private String ingredient;



    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
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
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}
