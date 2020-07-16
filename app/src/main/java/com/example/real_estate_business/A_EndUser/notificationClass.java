package com.example.real_estate_business.A_EndUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.real_estate_business.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class notificationClass extends AppCompatActivity {
    private static final String TAG = "notificationClass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_class);

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
                // navigate the app based on param
            }
        }
        Button loginButton = findViewById(R.id.logTokenButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(
                        new OnCompleteListener<InstanceIdResult>() {
                            @Override public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.e(TAG, "getInstanceId failed", task.getException());
                                    return;
                                }
                                // Get new Instance ID token
                                String token = task.getResult().getToken();
                                Log.d(TAG, token);
                                Toast.makeText(notificationClass.this, token, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
