package com.example.cocktailtime;

public class CocktailRequest {

    private String  naam;
    private int  sterkte;
    private String imageLocation;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getSterkte() {
        return sterkte;
    }

    public void setSterkte(int sterkte) {
        this.sterkte = sterkte;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
}
