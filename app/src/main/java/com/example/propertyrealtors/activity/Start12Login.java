package com.example.propertyrealtors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.City;
import com.example.propertyrealtors.model.User;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Start12Login extends AppCompatActivity {
    String email, name, usertype, phone, password, PURPOSE;
    EditText Email, Phone, Password;
    EditText editTextCode;
    User user;
    City city;
    EditText mcity;
    FirebaseAuth auth;
    DatabaseReference reference;
    SessionManager session;
    String code;
    long id=0;

    private static final String TAG= "VerifyPhoneActivity";
    String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start12_login);

        auth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Phone = findViewById(R.id.mobile);
        editTextCode = findViewById(R.id.otp);

        session= new SessionManager(getApplicationContext());
        editTextCode.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PURPOSE = bundle.getString("PURPOSE", null);
        name = bundle.getString("USER_NAME", null);
        usertype = bundle.getString("USER_TYPE", null);

        Log.e("Test 1", PURPOSE  + name  + usertype);

    }
    public void back(View view) {
        startActivity(new Intent(Start12Login.this, Start11.class));
        finish();
    }

    public void skip(View view) {
        startActivity(new Intent(Start12Login.this, MainActivity.class));
        finish();
    }

    public void next(View view) {
        email= Email.getText().toString();
        phone= Phone.getText().toString();
        password= Password.getText().toString();

        if (name.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Name?", Toast.LENGTH_LONG).show();
            Password.setError( "Name?");
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
        } else if (Phone.getText().toString().length() == 0) {
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
            editTextCode.setVisibility(View.VISIBLE);
            sendVerificationCode(phone);
        }
    }

    public void verify(View view) {
     /*   if(editTextCode.getText().toString().length()==0){
            editTextCode.setError("error");
        }else {
            verifyVerificationCode(code);
        }*/
    }

    private void sendVerificationCode(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phone,
            //    "+1" + phone,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    //Getting the code sent by SMS
                    code = phoneAuthCredential.getSmsCode();
                    //sometime the code is not detected automatically
                    //in this case the code will be null
                    //so user has to manually enter the code
                    if (code != null) {
                        editTextCode.setText(code);
                        //verifying the code
                        verifyVerificationCode(code);
                    }
                }


                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Log.w(TAG, "onVerificationFailed", e);
                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        editTextCode.setError("Invalid phone number.");
                    } else if (e instanceof FirebaseTooManyRequestsException) {
                        Snackbar.make(findViewById(R.id.content), "Quota exceeded.",
                                Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(Start12Login.this, "unlimited attempted !", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCodeSent(String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    //storing the verification id that is sent to the user
                    mVerificationId = s;
                }
            };

    private void verifyVerificationCode(String code) {
        try {
            //creating the credential
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            //signing the user
            signInWithPhoneAuthCredential(credential);
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(Start12Login.this, new OnCompleteListener<AuthResult>() {
                    @SuppressWarnings("unused")
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
                            session.createDetailsSession(name, email, phone);
                            Toast.makeText(Start12Login.this, "Registered", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Start12Login.this, Start13.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Bundle bundle = new Bundle();
                            bundle.putString("PURPOSE_OF_USER", PURPOSE);
                            startActivity(intent);
                            //  finish();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                editTextCode.setError("Invalid code.");
                            }
                        }
                    }
                });
    }


    public void submit(View view) {
     //   mcity= findViewById(R.id.city);
        String Mcity= mcity.getText().toString();
        reference= FirebaseDatabase.getInstance().getReference().child("City");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    id=(dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        city = new City(Mcity);
        reference.child(String.valueOf(id+1)).setValue(city);
     //   reference.child("City").setValue(city);
        mcity.setText("");
        Toast.makeText(Start12Login.this, "added", Toast.LENGTH_SHORT).show();
    }


}