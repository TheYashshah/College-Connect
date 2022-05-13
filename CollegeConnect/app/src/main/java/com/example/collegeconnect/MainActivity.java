package com.example.collegeconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

// import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    NetworkFragment networkFragment = new NetworkFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    PostFragment postFragment = new PostFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // for side navigation bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();

        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawerlayout);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close );
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //this will set Drawer icon
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_account_circle_24);


        navigationView.setNavigationItemSelectedListener(listener);

        //For bottom navigation view
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFragment).commit();
                        return true;
                    case R.id.network:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,networkFragment).commit();
                        return true;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,notificationFragment).commit();
                        return true;
                    case R.id.post:
                        Intent intent = new Intent(MainActivity.this, postactivity.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,profileFragment).commit();
                        return true;
                }

                return false;
            }
        });

        ImageView img = (ImageView) findViewById(R.id.chat);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Chat.class);
                startActivity(intent);
            }
        });

        ImageView img1 = (ImageView) findViewById(R.id.search);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, Search.class);
                startActivity(intent1);
            }
        });


    }

    public void logout(View View){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }

        return true;
    }

    private NavigationView.OnNavigationItemSelectedListener listener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (item.getItemId()){
                case R.id.video:
                    Intent intent = new Intent(MainActivity.this, Chat.class);
                    startActivity(intent);
                    break;
                case R.id.group:
                    Toast.makeText(MainActivity.this, "group selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bookmarks:
                    Toast.makeText(MainActivity.this, "bookmarks selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.questions:
                    Toast.makeText(MainActivity.this, "questions selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.jobs:
                    Toast.makeText(MainActivity.this, "jobs selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.events:
                    Toast.makeText(MainActivity.this, "events selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.courses:
                    Toast.makeText(MainActivity.this, "courses selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this, login.class));
                    finish();
                    break;

            }
            return true;
        }
    };

}