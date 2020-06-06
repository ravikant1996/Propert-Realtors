package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.propertyrealtors.R;

public class Start11 extends AppCompatActivity {
    Button Individual, Agent, skip, next;
    String name;
    EditText Name;
    String type;
    String USER_PURPOSE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start11);

        Individual = findViewById(R.id.individual);
        Agent = findViewById(R.id.agent);
        skip = findViewById(R.id.skip);
        next = findViewById(R.id.next);
        Name = findViewById(R.id.name);

        next.setEnabled(false);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            USER_PURPOSE = bundle.getString("PURPOSE_OF_USER", null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void select(View v) {

        if (v.getId() == R.id.individual){
            type = "individual";
            Individual.setEnabled(false);
            Agent.setEnabled(true);
            next.setEnabled(true);
        }
        else if (v.getId() == R.id.agent){
            type = "agent";
            Agent.setEnabled(false);
            Individual.setEnabled(true);
            next.setEnabled(true);
        }
    }

    public void next(View view) {
        name = Name.getText().toString();
        if (USER_PURPOSE.isEmpty()) {
            Toast.makeText(this, "Purpose of using!", Toast.LENGTH_SHORT).show();
        }else if (type.length() == 0) {
            Toast.makeText(this, "plese select type", Toast.LENGTH_SHORT).show();
        } else if (Name.getText().toString().length() == 0) {
            Name.setError("Enter your name");
        } else {
            Intent intent = new Intent(Start11.this, Start12Login.class);
            Bundle bundle = new Bundle();
            bundle.putString("PURPOSE", USER_PURPOSE);
            bundle.putString("USER_TYPE", type);
            bundle.putString("USER_NAME", name);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void back(View view) {
        startActivity(new Intent(Start11.this, StartActivty.class));
        finish();
    }

    public void skip(View view) {
        startActivity(new Intent(Start11.this, MainActivity.class));
        finish();
    }
}
