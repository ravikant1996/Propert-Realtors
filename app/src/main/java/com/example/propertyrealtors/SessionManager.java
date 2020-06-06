package com.example.propertyrealtors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.propertyrealtors.activity.MainActivity;
import com.example.propertyrealtors.activity.Start33;
import com.example.propertyrealtors.model.PropertyModel;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

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
    public static final String KEY_AID ="aid" ;

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_LOGINTYPE = "logintype";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";


    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }
    public void createRENT_RESI(ArrayList<PropertyModel> list1) {
        this.list1 = list1;
    }
    public ArrayList<PropertyModel> getcreateRENT_RESI() {
        return list1;
    }

   /* public void createRENT_RESI(ArrayList<PropertyModel> arrayList){
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("Set1", json);
        editor.commit();
    }
    public void createSELL_RESI(ArrayList<PropertyModel> arrayList){
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("Set2", json);
        editor.commit();
    }
*/
// login id passing
    public void createIdSession(String id){
        editor.putString(KEY_ID, id);
        editor.commit();
    }
    public HashMap<String, String> getUserIDs(){
        HashMap<String, String> data = new HashMap<String, String>();
        data.put(KEY_ID, pref.getString(KEY_ID, null));
        return data;
    }

    public void createDetailsSession(String name, String email, String phone){
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.commit();
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> data = new HashMap<>();
        // user name
        data.put(KEY_NAME, pref.getString(KEY_NAME, null));
        // user email id
        data.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        data.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
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
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Start33.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            _context.startActivity(i);
        }
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Add new Flag to start new Activity
     //   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);

    }

}
