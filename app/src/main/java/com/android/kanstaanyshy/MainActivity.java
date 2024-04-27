package com.android.kanstaanyshy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.kanstaanyshy.view.AboutUs;
import com.android.kanstaanyshy.view.HistoryFemousFragment;
import com.android.kanstaanyshy.view.Likes;
import com.android.kanstaanyshy.view.PLayListFragment;
import com.android.kanstaanyshy.view.Playlist;
import com.android.kanstaanyshy.view.RatingFragment;
import com.android.kanstaanyshy.view.Recomendation;
import com.android.kanstaanyshy.view.ShazamFunc;
import com.android.kanstaanyshy.view.VictorinaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private FloatingActionButton fab;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        fab = findViewById(R.id.fab);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Recomendation()).commit();
            navigationView.setCheckedItem(R.id.songs);
        }

        fab.setOnClickListener(view -> showBottomDialog());

        bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Recomendation()).commit();
            } else if (itemId == R.id.nav_settings) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Playlist()).commit();
            } else if (itemId == R.id.nav_share) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Likes()).commit();
            } else if (itemId == R.id.nav_about) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutUs()).commit();
            } else if (itemId == R.id.nav_logout) {
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void showBottomDialog() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShazamFunc()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.songs) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Recomendation()).commit();
        } else if (itemId == R.id.victorina) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VictorinaFragment()).commit();
        } else if (itemId == R.id.history_artist) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFemousFragment()).commit();
        } else if (itemId == R.id.about_us) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutUs()).commit();
        } else if (itemId == R.id.rating) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RatingFragment()).commit();
        } else if (itemId == R.id.nav_logout) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


//    DialogPage Edittext
//select in dialogpage
//searching
//playing musics buttons
//victorian and rating not same
//history searching
}