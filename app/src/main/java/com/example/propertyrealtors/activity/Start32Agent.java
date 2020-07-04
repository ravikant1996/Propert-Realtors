package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;

public class Start32Agent extends AppCompatActivity {
    String email, name, usertype, phone, password;
    EditText Name, Email, Phone, Password;
    EditText otpcode;
    String otp;
    SessionManager session;
    Button next, send;
    static final String TAG = "Start32Agent";
    String mVerificationId;
    User user;
    DatabaseReference reference;
    private String verificationCode;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start32_agent);




    }

    public void back(View view) {
        Intent intent = new Intent(Start32Agent.this, Start31.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void next(View view) {
    }

    public void send(View view) {

    }
}
