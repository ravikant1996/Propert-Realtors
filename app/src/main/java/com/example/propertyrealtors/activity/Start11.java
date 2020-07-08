package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.propertyrealtors.A_EndUser.Start331AllResidential_View;
import com.example.propertyrealtors.R;
import com.google.android.material.textfield.TextInputLayout;

public class Start11 extends AppCompatActivity {
    Button Individual, Agent, skip, next;
    String name;
    EditText Name;
    String type;
    String USER_PURPOSE;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start11);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start11.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Individual = findViewById(R.id.individual);
        Agent = findViewById(R.id.agent);
        next = findViewById(R.id.next);
        Individual.setBackgroundColor(Color.WHITE);
        Agent.setBackgroundColor(Color.WHITE);
        Name = findViewById(R.id.name);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            USER_PURPOSE = bundle.getString("PURPOSE_OF_USER");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 3) {
                    next.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void select(View v) {

        TextInputLayout textInputLayout7= findViewById(R.id.textInputLayout7);
        if (v.getId() == R.id.individual) {
            type = "individual";
            textInputLayout7.setVisibility(View.VISIBLE);
            Individual.setBackgroundColor(Color.LTGRAY);
            Individual.setTextColor(Color.WHITE);
            Agent.setBackgroundColor(Color.WHITE);
            Agent.setTextColor(Color.BLACK);
        } else if (v.getId() == R.id.agent) {
            type = "agent";
            textInputLayout7.setVisibility(View.VISIBLE);
            Individual.setTextColor(Color.BLACK);
            Individual.setBackgroundColor(Color.WHITE);
            Agent.setBackgroundColor(Color.LTGRAY);
            Agent.setTextColor(Color.WHITE);
        }
    }

    public void next(View view) {
        try {
            name = Name.getText().toString().trim();
            if (USER_PURPOSE.isEmpty()) {
                Toast.makeText(this, "Purpose of using!", Toast.LENGTH_SHORT).show();
            } else if (type.isEmpty() || type == null) {
                Toast.makeText(this, "Select any type", Toast.LENGTH_SHORT).show();
                return;
            } else if (Name.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Intent intent = new Intent(Start11.this, Start12Login.class);
                Bundle bundle = new Bundle();
                bundle.putString("PURPOSE", USER_PURPOSE);
                bundle.putString("USER_TYPE", type);
                bundle.putString("USER_NAME", name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } catch (NullPointerException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Start11.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();

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
            Intent intent = new Intent(Start11.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
