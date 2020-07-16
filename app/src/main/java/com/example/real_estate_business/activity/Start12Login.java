package com.example.real_estate_business.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;
import com.example.real_estate_business.model.City;
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

public class Start12Login extends AppCompatActivity {
    String email, name, usertype, phone, password, PURPOSE;
    EditText Email, Phone, Password;
    EditText otpcode;
    User user;
    City city;
    Button Next;
    private String verificationCode;
    EditText mcity;
    FirebaseAuth auth;
    DatabaseReference reference;
    SessionManager session;
    String code;
    long id = 0;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    Menu menu;
    private static final String TAG = "VerifyPhoneActivity";
    String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start12_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        auth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Phone = findViewById(R.id.mobile);
        otpcode = findViewById(R.id.otp);
        Next = findViewById(R.id.next);

        session = new SessionManager(getApplicationContext());
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            PURPOSE = bundle.getString("PURPOSE");
            name = bundle.getString("USER_NAME");
            usertype = bundle.getString("USER_TYPE");
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        StartFirebaseLogin();

        Log.e("Test 1", PURPOSE+" " + name +" " + usertype);

    }


    public void send(View view) {
        email = Email.getText().toString();
        phone = Phone.getText().toString();
        password = Password.getText().toString();

        if (name.isEmpty() || name == null) {
            Toast.makeText(getApplicationContext(),
                    "Name?", Toast.LENGTH_LONG).show();
            return;
        } else if (Email.getText().toString().length() == 0) {

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
        } else if (Phone.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Phone No?", Toast.LENGTH_LONG).show();
            Phone.setError("Phone No?");
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
        code = otpcode.getText().toString().trim();
        try {
            //creating the credential
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, code);
            signInWithPhoneAuthCredential(credential);
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private void sendVerificationCode(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phone,
                //    "+1" + phone,
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
                Toast.makeText(Start12Login.this, "verification completed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    //   otpcode.setError("Enter One Time Password");
                    Toast.makeText(Start12Login.this, "Enter One Time Password", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_LONG).show();
                    Toast.makeText(Start12Login.this, "unlimited attempted !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(Start12Login.this, "Code sent", Toast.LENGTH_LONG).show();
                otpcode.setVisibility(View.VISIBLE);
                Next.setVisibility(View.VISIBLE);
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(Start12Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            // FirebaseUser user = task.getResult().getUser();

                            //Register User----------------------------------
                            FirebaseUser getuser = auth.getCurrentUser();
                            String uid = getuser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference().child("User");

                            user = new User(usertype, name, email, phone, password, uid);
                            reference.child(uid).setValue(user);
                            session.createIdSession(uid);
                            session.createDetailsSession(name, email, phone, usertype);
                            Toast.makeText(Start12Login.this, "Registered", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Start12Login.this, Start13.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Bundle bundle = new Bundle();
                            bundle.putString("PURPOSE_OF_USER", PURPOSE);
                            Log.e(" ", PURPOSE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                otpcode.setError("Invalid code.");
                            }
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_skip, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_skip) {
            Intent intent = new Intent(Start12Login.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}