package com.example.propertyrealtors;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.propertyrealtors.model.PropertyModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SharedPreference {
    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "PropertyModel_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<PropertyModel> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.clear();
        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, PropertyModel product) {
        List<PropertyModel> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<PropertyModel>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, String propertyId) {
        ArrayList<PropertyModel> favorites = getFavorites(context);
        if (favorites != null) {
            Iterator<PropertyModel> iterator = favorites.iterator();
            while(iterator.hasNext()) {
                PropertyModel next = iterator.next();
                if(next.getKeyId().equals(propertyId)) {
                    iterator.remove();
                    saveFavorites(context, favorites);
                }
            }
        }
    }

    public ArrayList<PropertyModel> getFavorites(Context context) {
        SharedPreferences settings;
        List<PropertyModel> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            PropertyModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    PropertyModel[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<PropertyModel>(favorites);
        } else
            return null;

        return (ArrayList<PropertyModel>) favorites;
    }
}
