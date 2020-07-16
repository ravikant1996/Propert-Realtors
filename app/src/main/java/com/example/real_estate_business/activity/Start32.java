package com.example.real_estate_business.activity;

import android.content.Intent;
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
import androidx.appcompat.widget.Toolbar;

public class Start32 extends AppCompatActivity {
    String email, name, usertype, phone, password;
    EditText Name, Email, Phone, Password;
    EditText otpcode;
    String otp;
    SessionManager session;
    Button next, send;
    static final String TAG = "Start32";
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
        setContentView(R.layout.activity_start32);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Phone = findViewById(R.id.mobile);
        otpcode = findViewById(R.id.code);
        next = findViewById(R.id.next);

        session = new SessionManager(getApplicationContext());
        // login type checker

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            //    purpose = bundle.getString("PURPOSE", null);
            //   name = bundle.getString("USER_NAME", null);
            usertype = bundle.getString("USER_TYPE", null);
            if (usertype.equals("Agent")){
                toolbar.setTitle("Help property seekers contact with you");
            }
            Log.e(TAG, usertype);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StartFirebaseLogin();


    }

    public void back(View view) {
        Intent intent = new Intent(Start32.this, Start31.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    public void send(View view) {

        name = Name.getText().toString();
        email = Email.getText().toString();
        phone = Phone.getText().toString();
        password = Password.getText().toString();

        if (Name.getText().toString().length() == 0) {

            Toast.makeText(getApplicationContext(),
                    "Enter name ", Toast.LENGTH_LONG).show();
            //  Name.setError("Enter name ");

            return;
        } else if (Name.getText().toString().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    "Name should be more than 3 letters", Toast.LENGTH_LONG).show();
            //  Name.setError("Name should be more than 3 letters");

            return;
        } else if (Email.getText().toString().length() == 0) {

            Toast.makeText(getApplicationContext(),
                    "Enter Email", Toast.LENGTH_LONG).show();
            //    Email.setError("Enter Email");

            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                Email.getText().toString()).matches()) {

            Toast.makeText(getApplicationContext(), "Invalid Email",
                    Toast.LENGTH_LONG).show();
            //  Email.setError("Invalid Email");
            return;
        } else if (Phone.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Enter a valid Phone number", Toast.LENGTH_LONG).show();
            //    Phone.setError("Enter a valid Phone number");
            return;
        } else if (Phone.getText().toString().length() < 10) {
            int no = Phone.getText().toString().length();
            Toast.makeText(getApplicationContext(),
                    "You have enter only " + no + " digits in Phone Number", Toast.LENGTH_LONG).show();
        } else if (Password.getText().toString().length() == 0 || Password.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(),
                    "Password is not empty or less than 6 digit", Toast.LENGTH_LONG).show();
            //  Password.setError( "Password is not empty or less than 6 digit");
            return;
        } else {
            sendVerificationCode(phone);
        }
    }

    public void next(View view) {
        otp = otpcode.getText().toString().trim();
        try {
            //creating the credential
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
            SigninWithPhone(credential);
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
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
                    otpcode.setText(code);
                    //verifying the code
                    //  verifyVerificationCode(code);
                }
                Toast.makeText(Start32.this, "verification completed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                 //   otpcode.setError("Enter One Time Password");
                    Toast.makeText(Start32.this, "Enter One Time Password", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_LONG).show();
                    Toast.makeText(Start32.this, "unlimited attempted !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(Start32.this, "Code sent", Toast.LENGTH_LONG).show();
                otpcode.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        };
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
                            Toast.makeText(Start32.this, "Registered", Toast.LENGTH_LONG).show();
                            session.createIdSession(uid);
                            session.createDetailsSession(name, email, phone, usertype);
                            Intent intent = new Intent(Start32.this, Start33.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Bundle bundle = new Bundle();
                            bundle.putString("UID", uid);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Start32.this, "Incorrect OTP", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}