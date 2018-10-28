package com.amrita.gpms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.amrita.gpms.warden_login.user_war;

public class student_tab extends AppCompatActivity {

private ProgressDialog progress1;

    private BottomNavigationView navigation;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_tab);

        Snackbar.make(findViewById(R.id.frame_lay),"Login successful", Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(android.R.color.holo_green_dark)).show();

        frameLayout=(FrameLayout)findViewById(R.id.frame_lay);
         navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setFragment(new Tab1_request());
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        setFragment(new Tab1_request());
                        return true;
                    case R.id.navigation_dashboard:
                        setFragment(new Tab2_view());
                        return true;
                    case R.id.navigation_logout:
                       MainActivity.ret=true;
                        openIntent();
                        return true;
                    default:return false;
                }
            }
        });
    }
private  void setFragment(Fragment fragment)
{
    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.frame_lay,fragment);
    fragmentTransaction.commit();
}
    private  void  showdialog()
    {    progress1 = new ProgressDialog(this);

        progress1.setTitle("Fetching Data");
        progress1.setMessage("Please wait...");
        progress1.setCancelable(false); // disable dismiss by tapping outside of the dialog
    }
    private void openIntent()
    {
        Intent intent;

        intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }
}
