package com.example.real_estate_business.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;
import com.example.real_estate_business.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.graphics.Color.WHITE;

public class Sign_Up extends AppCompatActivity {
    EditText Name, Phone, Email, Otp, Password;
    String email, name, usertype, phone, password, otp;
    Button owner, agent, Verify;
    static final String TAG= "SignUp";
    User user;
    DatabaseReference reference;
    private String verificationCode;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        session= new SessionManager(getApplicationContext());

        Name= findViewById(R.id.name);
        Phone= findViewById(R.id.phone);
        Email= findViewById(R.id.email);
        Password= findViewById(R.id.password);
        Otp = findViewById(R.id.otp);
        Verify = findViewById(R.id.button2);
        agent = findViewById(R.id.agent);
        owner = findViewById(R.id.buyer);

        StartFirebaseLogin();


    }

    public void back(View view) {
        startActivity(new Intent(Sign_Up.this, Login.class));
        finish();
    }

    public void onRadioButtonClicked(View v) {
        if (v.getId() == R.id.buyer){
            usertype = "Owner";
            owner.setBackgroundColor(Color.RED);
            owner.setTextColor(WHITE);
            agent.setBackgroundColor(WHITE);
        }
        else if (v.getId() == R.id.agent){
            usertype = "Agent";
            agent.setBackgroundColor(Color.RED);
            agent.setTextColor(WHITE);
            owner.setBackgroundColor(WHITE);
        }
    }

    public void send(View view) {

        name= Name.getText().toString();
        email= Email.getText().toString();
        phone= Phone.getText().toString();
        password= Password.getText().toString();
        if (usertype.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Setect Type", Toast.LENGTH_LONG).show();
            Email.setError("Setect Type");

            return;
        }else if (Name.getText().toString().length() == 0) {

            Toast.makeText(getApplicationContext(),
                    "Name cannot be Blank", Toast.LENGTH_LONG).show();
            Email.setError("Name cannot be Blank");

            return;
        }else if (Email.getText().toString().length() == 0) {

            Toast.makeText(getApplicationContext(),
                    "Email cannot be Blank", Toast.LENGTH_LONG).show();
            Email.setError("Email cannot be Blank");

            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                Email.getText().toString()).matches()) {

            Toast.makeText(getApplicationContext(), "Invalid Email",
                    Toast.LENGTH_LONG).show();
            Email.setError("Invalid Email");
            return;
        } else if (!(Phone.getText().toString().length() == 10)) {
            Toast.makeText(getApplicationContext(),
                    "Phone No?", Toast.LENGTH_LONG).show();
            Phone.setError("Phone No?");
            return;
        } else if (Password.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(),
                    "Password?", Toast.LENGTH_LONG).show();
            Password.setError( "Password?");
            return;
        }else {
            Otp.setVisibility(View.VISIBLE);
            Verify.setVisibility(View.VISIBLE);
            sendVerificationCode(phone);
        }
    }

    private void sendVerificationCode(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phone,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallback);
    }
    private void StartFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    Otp.setText(code);
                    //verifying the code
                    //  verifyVerificationCode(code);
                }
                Toast.makeText(Sign_Up.this,"verification completed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Otp.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(Sign_Up.this, "unlimited attempted !", Toast.LENGTH_SHORT).show();
                }            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(Sign_Up.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void next(View view) {
        otp = Otp.getText().toString();
        try {
            //creating the credential
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
            SigninWithPhone(credential);
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser getuser = auth.getCurrentUser();
                            String uid = getuser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference().child("User");

                            user = new User(usertype, name, email, phone, password, uid);
                            reference.child(uid).setValue(user);
                            Toast.makeText(Sign_Up.this, "Registered", Toast.LENGTH_SHORT).show();
                            session.createIdSession(uid);
                            session.createDetailsSession(name, email, phone, usertype);
                            Intent intent = new Intent(Sign_Up.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Sign_Up.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void policy(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://property-becho-n-kha.flycricket.io/privacy.html")));
    }
}
