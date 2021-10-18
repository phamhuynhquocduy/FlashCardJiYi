package org.o7planning.yiji2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.google.android.material.navigation.NavigationView;

import org.o7planning.yiji2.ui.ChangePassFragment;
import org.o7planning.yiji2.ui.HistoryFragment;
import org.o7planning.yiji2.ui.HomeFragment;
import org.o7planning.yiji2.ui.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    int HOME_FRAGMENT =1;
    int HISTORY_FRAGMENT =2;
    int PROFILE_FRAGMENT=3;
    int PASS_FRAGMENT=4;
    private  int currentFragment=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setUpNavDrawer();
    }
    public void setUpNavDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //Hien thi trang Home
        replaceFragment(new HomeFragment());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_log_out:
                        SharedPreferences.Editor editor = getSharedPreferences("preference_login_key", MODE_PRIVATE).edit();
                        editor.putString("preference_login_status",null);
                        editor.apply();
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        finish();
                    case R.id.nav_home:
                        if(currentFragment!=HOME_FRAGMENT){
                            replaceFragment(new HomeFragment());
                            currentFragment=HOME_FRAGMENT;
                        }
                        break;
                    case R.id.nav_history:
                        if(currentFragment!=HISTORY_FRAGMENT){
                            replaceFragment(new HistoryFragment());
                            currentFragment= HISTORY_FRAGMENT;
                        }
                        break;
                    case R.id.nav_change_pass:
                        if(currentFragment!=PASS_FRAGMENT){
                            replaceFragment(new ChangePassFragment());
                            currentFragment= PASS_FRAGMENT;
                        }
                        break;
                    case R.id.nav_user:
                        if(currentFragment!=PROFILE_FRAGMENT){
                            replaceFragment(new ProfileFragment());
                            currentFragment= PROFILE_FRAGMENT;
                        }
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private  void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.commit();

    }
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("count",String.valueOf(count));
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}