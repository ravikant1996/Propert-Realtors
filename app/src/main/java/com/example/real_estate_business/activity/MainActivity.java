package com.example.real_estate_business.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_estate_business.A_EndUser.Notification;
import com.example.real_estate_business.A_EndUser.dashboard;
import com.example.real_estate_business.A_EndUser.dashboard_EndUser;
import com.example.real_estate_business.A_EndUser.enduser_profile;
import com.example.real_estate_business.A_EndUser.shortlisted_property;
import com.example.real_estate_business.HideBottomViewOnScrollBehavior;
import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import androidx.annotation.NonNull;
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
    Toolbar toolbar;
    DatabaseReference reference;
    String UID, propertyFor, propertyType;
    SessionManager session;
    Menu menu;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    //double tap of exit
    private static final int TIME_INTERVAL = 4000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        drawerLayout = (DrawerLayout) findViewById(R.id.enduser_drawer);
        navigationbottom = findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        try {

            HashMap<String, String> userID = session.getUserIDs();
            UID = userID.get(SessionManager.KEY_ID);
            //  getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  dashboardEndUser();
        toolbar.setTitle("Home");
        loadFragment(new dashboard_EndUser());

        //getting bottom navigation view and attaching the listener
        navigationbottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationbottom.getLayoutParams();
        layoutParams.setBehavior(new HideBottomViewOnScrollBehavior());

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;


        navigationView = findViewById(R.id.enduser_navigation);
        View headerView = navigationView.inflateHeaderView(R.layout.enduser_navigation_header);
//        drawerHeaderImage = (CircleImageView) headerView.findViewById(R.id.profile_image);
        endUserName = (TextView) headerView.findViewById(R.id.loginTextId);
        EmailId = (TextView) headerView.findViewById(R.id.id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                SessionManager session = new SessionManager(getApplicationContext());
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                switch (item.getItemId()) {
                    case R.id.buy_property:
                        session.createSearchSession("residential", "SELL");
                        Fragment fragment = new dashboard_EndUser();
                        loadFragment(fragment);
                        break;
                    case R.id.rent_property:
                        session.createSearchSession("residential", "RENT or LEASE");
                        Fragment fragment1 = new dashboard_EndUser();
                        loadFragment(fragment1);
                        break;
                   /* case R.id.new_project:
                        Toast.makeText(MainActivity.this, "new", Toast.LENGTH_SHORT).show();

                        break;*/
                    case R.id.share:
                        Toast.makeText(MainActivity.this, "share", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.rates:
                        Toast.makeText(MainActivity.this, "rates", Toast.LENGTH_SHORT).show();

                        break;
                   /* case R.id.localities:
                        Toast.makeText(MainActivity.this, "local", Toast.LENGTH_SHORT).show();
                        break;*/
                    case R.id.post_property:
                        try {
                            if (TextUtils.isEmpty(UID)) {
                                Intent intent = new Intent(MainActivity.this, Start31.class);
                                Bundle bundle = new Bundle();
                                Boolean Signal = true;
                                bundle.putBoolean("SiGNAL", Signal);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent1 = new Intent(MainActivity.this, Start33.class);
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("UID", UID);
                                intent1.putExtras(bundle1);
                                startActivity(intent1);
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.logout:
                        Toast.makeText(MainActivity.this, "logout", Toast.LENGTH_SHORT).show();
                        session.logoutUser();
                        break;
                    case R.id.login:
//                        Toast.makeText(MainActivity.this, "login", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, Login.class);
                        startActivity(intent1);
                        finish();
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


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.post_menu, menu);
         return true;

     }

     public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();

         if (id == R.id.app_bar_post) {
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
         } else if (id == R.id.favourite) {
             Intent intent = new Intent(MainActivity.this, shortlisted_property.class);
             startActivity(intent);
             return true;
         }
         return super.onOptionsItemSelected(item);

     }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                        fragment = new dashboard_EndUser();
                    break;

                case R.id.navigation_dashboard:
                    fragment = new dashboard();
                    toolbar.setTitle("Dashboard");
                    break;

                case R.id.navigation_notifications:
                    fragment = new Notification();
                    toolbar.setTitle("Notification");
                    break;

                case R.id.navigation_profile:
               /*     Intent intent = new Intent(MainActivity.this, notificationClass.class);
                    startActivity(intent);*/
                    fragment = new enduser_profile();
                    toolbar.setTitle("Profile");
                    break;
            }
            return loadFragment(fragment);
        }
    };
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
           finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Press back if you want to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

}
