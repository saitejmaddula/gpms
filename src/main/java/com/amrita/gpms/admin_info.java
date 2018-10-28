package com.amrita.gpms;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toolbar;

public class admin_info extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView navigation;
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info);
        Snackbar.make(findViewById(R.id.admin_inf),"Login successful", Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(android.R.color.holo_green_dark)).show();
        android.support.v7.widget.Toolbar toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_options) ;
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.admin_inf);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        frameLayout=(FrameLayout)findViewById(R.id.frame_lay1);
        navigation = (BottomNavigationView) findViewById(R.id.navigation1);
        setFragment(new adtab_stu1());
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_stu:
                        setFragment(new adtab_stu1());
                        return true;
                    case R.id.navigation_war:
                        setFragment(new adtabwar1());
                        return  true;
                    case R.id.navigation_view:
                        setFragment(new adtab_view1());
                        return  true;
                    default:return false;
                }
            }
        });
        NavigationView view=(NavigationView)findViewById(R.id.nav_view);
        view.setNavigationItemSelectedListener(this);
    }

    private  void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_lay1,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
       if(drawerLayout.isDrawerOpen(Gravity.START))
       {
           drawerLayout.closeDrawer(Gravity.START);
       }
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.logout_admin:
                MainActivity.ret=true;
                Intent intent=new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
        }
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }
}
