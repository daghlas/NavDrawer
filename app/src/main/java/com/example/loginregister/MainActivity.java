package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.orange));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Intent back = new Intent(getApplicationContext(), MainActivity.class);
                            //startActivity(back);
                        }
                    })
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //finish();
                            System.exit(0);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_staff:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StaffFragment()).commit();
                break;
            case R.id.nav_cust:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustFragment()).commit();
                break;
            case R.id.nav_project:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProjectFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Share APP coming soon", Toast.LENGTH_LONG).show();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Notification()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.btn_search).getActionView();
        searchView.setQueryHint("Search..");

        //closes search view
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            searchView.onActionViewCollapsed();
        }

        //sets color on menu texts
        //new BackgroundColorSpan(Color.WHITE);
        for (int i = 0; i<menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            SpannableString spannableString = new SpannableString(
                    menu.getItem(i).getTitle().toString()
            );
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)),
                    0,spannableString.length(),0);
            menuItem.setTitle(spannableString);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_settings:
                Toast.makeText(this, "Settings coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_refresh:
                Toast.makeText(this, "Refresh coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_reach:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReachMenuFragment()).commit();
                break;
            case R.id.menu_help:
                Toast.makeText(this, "Help coming soon", Toast.LENGTH_SHORT).show();
                break;
        }

        super.onOptionsItemSelected(item);
        return true;
    }

/*
    @Override
    public void onResume() {
        super.onResume();
        if (hasPermission())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 101);
        else if (hasPermission())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 101);
        if (hasPermission())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 101);
        else if (hasPermission())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 101);
    }
    private boolean hasPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
        );
        return false;
    }

 */
}