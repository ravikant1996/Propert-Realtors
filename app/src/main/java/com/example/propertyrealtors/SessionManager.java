package com.example.propertyrealtors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.propertyrealtors.activity.MainActivity;
import com.example.propertyrealtors.activity.Start33;
import com.example.propertyrealtors.model.PropertyModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    static final String PREF_NAME = "Reg";

    private ArrayList<PropertyModel> list1 = new ArrayList<>();
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_ID = "id";
    public static final String KEY_PID_ID = "pid";
    public static final String KEY_AID = "aid";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_USERTYPE = "usertype";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String PROPERTY_KEY = "propertyId";
    public static final String PROPERTYTYPE_KEY = "propertytype";
    public static final String PROPERTYFOR_KEY = "propertyfor";
    public static final String PROPERTYSUBTYPE_KEY = "propertysubtype";

    public static final String FAVORITES = "favorite";

    public static final String BEDROOM_KEY = "bedroom";
    public static final String S_PROPERTYKEY = "s_keyId";
    public static final String S_PROPERTYTYPE = "s_type";
    public static final String S_ADDRESS = "s_address";
    public static final String S_BEDROOM = "s_bedroom";
    public static final String S_PRICE = "s_price";
    public static final String S_TIME = "s_time";


    // Constructor
    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    //session of property
    public void saveArrayList(ArrayList<PropertyModel> list, String key){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.commit();     // This line is IMPORTANT !!!
    }
    public ArrayList<PropertyModel> getArrayList(String key){
        Gson gson = new Gson();
        String json = pref.getString(key, null);
        Type type = new TypeToken<ArrayList<PropertyModel>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveFavorites(List<PropertyModel> favorites){
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(FAVORITES, jsonFavorites);
        editor.commit();
    }

    public void addFavorite(PropertyModel code){
        List<PropertyModel> favorites = getFavorites();

        if(favorites == null)
            favorites = new ArrayList<PropertyModel>();
        favorites.add(code);
        saveFavorites(favorites);
    }

    public void removeFavorite(PropertyModel details) {
        ArrayList<PropertyModel> favorites = getFavorites();
        if (favorites != null) {
            if (favorites.equals(details)) {
                favorites.remove(details);
                saveFavorites(favorites);
            }
        }
    }

    public ArrayList<PropertyModel> getFavorites() {
        ArrayList<PropertyModel> favorites;

        if (pref.contains(FAVORITES)) {
            String jsonFavorites = pref.getString(FAVORITES, null);
            Gson gson = new Gson();
            PropertyModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    PropertyModel[].class);

            favorites  = new ArrayList<PropertyModel>();
            favorites.addAll(Arrays.asList(favoriteItems));
        } else
            return null;

        return favorites;
    }
    //Search session
    public void createSearchSession(String propertyType, String propertyfor) {
        editor.putString(PROPERTYTYPE_KEY, propertyType);
        editor.putString(PROPERTYFOR_KEY, propertyfor);
        editor.commit();
    }

    public HashMap<String, String> getPropertySearchSession() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put(PROPERTYTYPE_KEY, pref.getString(PROPERTYTYPE_KEY, null));
        data.put(PROPERTYFOR_KEY, pref.getString(PROPERTYFOR_KEY, null));
        return data;
    }

    // login id passing
    public void createIdSession(String id) {
        editor.putString(KEY_ID, id);
        editor.commit();
    }

    public HashMap<String, String> getUserIDs() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put(KEY_ID, pref.getString(KEY_ID, null));
        return data;
    }

    public void createDetailsSession(String name, String email, String phone, String usertype) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_USERTYPE, usertype);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> data = new HashMap<>();
        // user name
        data.put(KEY_NAME, pref.getString(KEY_NAME, null));
        // user email id
        data.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        data.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        //
        data.put(KEY_USERTYPE, pref.getString(KEY_USERTYPE, null));
        // return user
        return data;
    }


   /* public void createLoginSession(String uid){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_LOGINTYPE, logintype);
        // Storing name in pref
        editor.putString(KEY_NAME, name);

    //    editor.putString(KEY_PASSWORD, password);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }
   */

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, Start33.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            context.startActivity(i);
        }
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Add new Flag to start new Activity
        //   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        context.startActivity(i);

    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);

    }

}
