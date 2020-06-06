package com.example.propertyrealtors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.ResidentialModel;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText UserName, Otp, Password;
    Button Login, Register, VerifyBtn;
    String unameORphone, password, otp;
    static final String TAG = "Login";
    User user;
    DatabaseReference reference;
    private String verificationCode;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        session = new SessionManager(getApplicationContext());
        UserName = findViewById(R.id.name);
        Login = findViewById(R.id.login);
        Register = findViewById(R.id.register);
        VerifyBtn = findViewById(R.id.button2);
        Otp = findViewById(R.id.otp);
        Password = findViewById(R.id.password);


        StartFirebaseLogin();


    }

    public void back(View view) {
        startActivity(new Intent(Login.this, MainActivity.class));
        finish();
    }

    public void login(View view) {
        unameORphone = UserName.getText().toString().trim();
        password = Password.getText().toString().trim();

        if (UserName.getText().toString().length() == 0) {

            Toast.makeText(getApplicationContext(),
                    "UserName cannot be Blank", Toast.LENGTH_LONG).show();
            UserName.setError("UserName cannot be Blank");
            return;
        } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(
                UserName.getText().toString()).matches()) {
            Password.setVisibility(View.VISIBLE);
            if (Password.getText().toString().length() == 0) {
                Toast.makeText(getApplicationContext(),
                        "Enter Password", Toast.LENGTH_LONG).show();
              //  Password.setError("Password?");
                return;
            } else {
                validateEmail();
             //   loginWithEmailandPassword();
            }
            return;
        } else if (Patterns.PHONE.matcher(UserName.getText().toString()).matches()) {
            Password.setVisibility(View.GONE);
            if (!(UserName.getText().toString().length() == 10)) {
                int no= UserName.getText().toString().length();
                Toast.makeText(getApplicationContext(),
                        "You have enter only "+ no+" digit", Toast.LENGTH_LONG).show();
               // UserName.setError("You have enter only "+ no+" digit");
                return;
            } else {
                validatePhone();

            }
        }
    }

    private void validateEmail() {
        reference= FirebaseDatabase.getInstance().getReference().child("User");
        Query checkUser = reference.orderByChild("email").equalTo(unameORphone);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        User data = child.getValue(User.class);
                        String pass = data.getPassword();
                        if (pass.equals(password)) {
                            String uid = data.getId();
                            Toast.makeText(Login.this, "Registered", Toast.LENGTH_SHORT).show();
                            session.createIdSession(uid);
                            getDetails(uid);
                        }
                    }
                }else{
                    UserName.setError("Wrong Password");
                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Login.this, "First registered the Email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void validatePhone(){
        reference= FirebaseDatabase.getInstance().getReference().child("User");
        Query checkUser = reference.orderByChild("phone").equalTo(unameORphone);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    VerifyBtn.setVisibility(View.VISIBLE);
                    Otp.setVisibility(View.VISIBLE);
                    sendVerificationCode(unameORphone);
                }else{
                    UserName.setError("First registered the number");
                    Toast.makeText(Login.this, "First registered the number", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void sendVerificationCode(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+1" + phone,
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
                Toast.makeText(Login.this, "verification completed", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Otp.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(Login.this, "unlimited attempted !", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(Login.this, "Code sent", Toast.LENGTH_SHORT).show();
            }
        };
    }


    private void loginWithEmailandPassword() {
        auth.signInWithEmailAndPassword(unameORphone, password).addOnCompleteListener(
                Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser getuser = auth.getCurrentUser();
                            String uid = getuser.getUid();
                            Toast.makeText(Login.this, "Registered", Toast.LENGTH_SHORT).show();
                            session.createIdSession(uid);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        } else {
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void register(View view) {
        startActivity(new Intent(Login.this, Sign_Up.class));
        finish();
    }

    public void terms(View view) {

    }

    public void Verify(View view) {
        otp = Otp.getText().toString();
        try {
            //creating the credential
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
            SigninWithPhone(credential);
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
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
                            Toast.makeText(Login.this, "Login", Toast.LENGTH_SHORT).show();
                            session.createIdSession(uid);
                            getDetails(uid);
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Otp.setError("Invalid code.");
                            }
                        }
                    }
                });
    }
    private void getDetails(String id) {
        reference = FirebaseDatabase.getInstance().getReference().child("User").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    session.createDetailsSession(name, email, phone);
                    Intent intent = new Intent(Login.this, MainActivity.class);
                  /*  Bundle bundle = new Bundle();
                    bundle.putString("NAME", name);
                    intent.putExtras(bundle);*/
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}