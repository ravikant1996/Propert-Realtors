package com.example.propertyrealtors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.propertyrealtors.activity.MainActivity;
import com.example.propertyrealtors.activity.Start31;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminPushNotifi extends AppCompatActivity {
    EditText edtTitle;
    EditText edtMessage;
    Button btnSend;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAaPAmpiw:APA91bETTXoFudXAx7UZDuuodoDumg91ncsZTErHBOGjDV-ymRc4wuihHxbkBJbmxuxTAXaFf6WP52QER9-KGOVwf8GocjWwfkJlB6VXotvrgYESfR14ad3An1qELnFMaJj_HS3uHtor";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;
    RequestQueue mRequestQue;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_push_notifi);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdminPushNotifi.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        edtTitle = findViewById(R.id.edtTitle);
        edtMessage = findViewById(R.id.edtMessage);
         btnSend = findViewById(R.id.btnSend);
//         mRequestQue= Volley.newRequestQueue(this);
//        FirebaseMessaging.getInstance().subscribeToTopic("news");


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTitle.getText().toString().length()==0){
                    Toast.makeText(AdminPushNotifi.this, "Enter title", Toast.LENGTH_SHORT).show();
                    return;
                }else if (edtMessage.getText().toString().length()==0){
                    Toast.makeText(AdminPushNotifi.this, "Enetr message", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    TOPIC = "/topics/news"; //topic must match with what the receiver subscribed to
                    NOTIFICATION_TITLE = edtTitle.getText().toString();
                    NOTIFICATION_MESSAGE = edtMessage.getText().toString();

                    JSONObject notification = new JSONObject();
                    JSONObject notifcationBody = new JSONObject();
                    try {
                        notifcationBody.put("title", NOTIFICATION_TITLE);
                        notifcationBody.put("message", NOTIFICATION_MESSAGE);

                        notification.put("to", TOPIC);
                        notification.put("data", notifcationBody);

                        sendNotification(notification);

                    } catch (JSONException e) {
                        Log.e(TAG, "onCreate: " + e.getMessage());
                    }
                }

            }
        });
    }

    private void sendNotification(final JSONObject notification) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, FCM_API, notification,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(AdminPushNotifi.this, "Success", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: " + response.toString());

                            edtTitle.setText("");
                            edtMessage.setText("");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, "onErrorResponse: Didn't work");
                    Log.i(TAG, "onResponse: " + error.networkResponse);
                    Log.i(TAG, "json: " + notification);

                    Toast.makeText(AdminPushNotifi.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", serverKey);
                    params.put("Content-Type", contentType);
                    return params;
                }
            };
//        mRequestQue.add(jsonObjectRequest);
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}