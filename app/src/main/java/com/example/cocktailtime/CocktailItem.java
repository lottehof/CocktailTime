package com.example.cocktailtime;

public class CocktailItem {
    private String mImageUrl;
    private String mCocktailName;
    private int mStrength;

    public CocktailItem(String imageUrl, String cocktailName, int strength ){
        mImageUrl = imageUrl;
        mCocktailName = cocktailName;
        mStrength = strength;

    }

    public String getImageUrl() {
        return mImageUrl;
    }
    public String getCocktailName() {
        return mCocktailName;
    }

    public int getStrength(){
        return mStrength;
    }


}
