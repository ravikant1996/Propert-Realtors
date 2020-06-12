package com.example.propertyrealtors.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.A_EndUser.Notification;
import com.example.propertyrealtors.A_EndUser.dashboard;
import com.example.propertyrealtors.A_EndUser.dashboard_EndUser;
import com.example.propertyrealtors.A_EndUser.enduser_profile;
import com.example.propertyrealtors.HideBottomViewOnScrollBehavior;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    View view;
    String name, email, phone;
     DrawerLayout drawerLayout;
     ActionBarDrawerToggle actionBarDrawerToggle;
     NavigationView navigationView;
    BottomNavigationView navigationbottom;
    CircleImageView drawerHeaderImage;
    TextView endUserName, EmailId;
    FirebaseAuth auth;
    FirebaseUser getuser;
    private ActionBar toolbar;
    Toolbar toolbar1;
    DatabaseReference reference;
    String UID;
    SessionManager session;

    //double tap of exit
    private static final int TIME_INTERVAL = 4000;
    private long mBackPressed;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                //Catch your exception
                // Without System.exit() this will not work.
                System.exit(2);
            }
        });

        session = new SessionManager(getApplicationContext());
        drawerLayout = (DrawerLayout) findViewById(R.id.enduser_drawer);
        navigationbottom = findViewById(R.id.navigation);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar);

//        navigationView.bringToFront();
        //  drawerLayout.requestLayout();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar1, R.string.Open, R.string.Close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (getSupportActionBar() != null) {
            setSupportActionBar(toolbar1);
        }
      //  toolbar = getSupportActionBar();
        try {
            HashMap<String, String> userID = session.getUserIDs();
            UID = userID.get(SessionManager.KEY_ID);
            //  getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  dashboardEndUser();
        toolbar1.setTitle("Home");
        loadFragment(new dashboard_EndUser());

        //getting bottom navigation view and attaching the listener
        navigationbottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationbottom.getLayoutParams();
        layoutParams.setBehavior(new HideBottomViewOnScrollBehavior());


        navigationView = findViewById(R.id.enduser_navigation);
        View headerView = navigationView.inflateHeaderView(R.layout.enduser_navigation_header);
        drawerHeaderImage = (CircleImageView) headerView.findViewById(R.id.profile_image);
        endUserName = (TextView) headerView.findViewById(R.id.loginTextId);
        EmailId = (TextView) headerView.findViewById(R.id.id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                switch (item.getItemId()) {
                    case R.id.buy_property:
                        Toast.makeText(MainActivity.this, "buy", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.rent_property:
                        Toast.makeText(MainActivity.this, "rent", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.new_project:
                        Toast.makeText(MainActivity.this, "new", Toast.LENGTH_SHORT).show();

                    case R.id.share:
                        Toast.makeText(MainActivity.this, "share", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.rates:
                        Toast.makeText(MainActivity.this, "rates", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.localities:
                        Toast.makeText(MainActivity.this, "local", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.post_property:
                        try {
                            if (TextUtils.isEmpty(UID)) {
                                Intent intent = new Intent(MainActivity.this, Start31.class);
                                Bundle bundle = new Bundle();
                                Boolean Signal = true;
                                bundle.putBoolean("SiGNAL", Signal);
                                intent.putExtras(bundle);
                                getApplicationContext().startActivity(intent);
                            } else {
                                Intent intent = new Intent(MainActivity.this, Start33.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("UID", UID);
                                intent.putExtras(bundle);
                                getApplicationContext().startActivity(intent);
                            }
                            Toast.makeText(MainActivity.this, "post", Toast.LENGTH_SHORT).show();
                        }catch (AndroidRuntimeException e){
                            e.printStackTrace();
                        }finally {
                            Log.e("MainActivity", "caught error");
                        }
                        break;
                    case R.id.logout:
                        Toast.makeText(MainActivity.this, "logout", Toast.LENGTH_SHORT).show();
                        session.logoutUser();
                        break;
                    case R.id.login:
                        Toast.makeText(MainActivity.this, "login", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, Login.class);
                        startActivity(intent1);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManager.KEY_NAME);
        Menu menu = navigationView.getMenu();
        if (!TextUtils.isEmpty(name)) {
            endUserName.setText(name);
            menu.findItem(R.id.logout).setVisible(true);
            menu.findItem(R.id.login).setVisible(false);
            return;
        } else {
            menu.findItem(R.id.logout).setVisible(false);
            menu.findItem(R.id.login).setVisible(true);
            endUserName.setText("Login");
            endUserName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

   /* @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            //   this.finishAffinity();
        }
    }*/


   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        return super.onOptionsItemSelected(item);

    }*/
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.post_menu, menu);
       return true;
   }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_post:
                Toast.makeText(MainActivity.this, "Toolbar Button Clicked!", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                if (TextUtils.isEmpty(UID)) {
                    Intent intent = new Intent(MainActivity.this, Start31.class);
                    Boolean Signal = true;
                    bundle.putBoolean("SiGNAL", Signal);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, Start33.class);
                    bundle.putString("UID", UID);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                return true;
            case R.id.favourite:
                Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(getApplicationContext(), "clicked1", Toast.LENGTH_SHORT).show();
                    fragment = new dashboard_EndUser();
                    toolbar1.setTitle("Home");
                    break;

                case R.id.navigation_dashboard:
                    Toast.makeText(getApplicationContext(), "clicked2", Toast.LENGTH_SHORT).show();
                    //  startActivity(new Intent(MainActivity.this, posted_property.class));
                    fragment = new dashboard();
                    toolbar1.setTitle("Dashboard");
                    break;

                case R.id.navigation_notifications:
                    Toast.makeText(getApplicationContext(), "clicked3", Toast.LENGTH_SHORT).show();
                    fragment = new Notification();
                    toolbar1.setTitle("Notification");
                    break;

                case R.id.navigation_profile:
                    Toast.makeText(getApplicationContext(), "clicked4", Toast.LENGTH_SHORT).show();
                    fragment = new enduser_profile();
                    toolbar1.setTitle("Profile");
                    break;
            }
            return loadFragment(fragment);
        }
    };

 /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        MenuItem item = menu.findItem(R.id.app_bar_post);
        Button btn = item.getActionView().findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Toolbar Button Clicked!", Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                if(TextUtils.isEmpty(UID)) {
                    Intent intent= new Intent(MainActivity.this, Start31.class);
                    Boolean Signal = true;
                    bundle.putBoolean("SiGNAL", Signal);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent= new Intent(MainActivity.this, Start33.class);
                    bundle.putString("UID", UID);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
        return true;
    }*/

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.enduser_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Press back if you want to exit",    Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
