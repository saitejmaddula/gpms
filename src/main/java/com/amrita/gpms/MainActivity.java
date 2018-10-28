package com.amrita.gpms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView student,warden,admin,security;
    public static boolean ret=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ret)
        {   ret=false;
            Snackbar.make(findViewById(R.id.mnact),"Logout successful.", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_green_dark)).show();

        }
        student=(CardView)findViewById(R.id.stu_card);
        warden =(CardView)findViewById(R.id.war_card);
        admin=(CardView)findViewById(R.id.admin_card);
        security=(CardView)findViewById(R.id.sec_card);
        student.setOnClickListener(this);
        warden.setOnClickListener(this);
        admin.setOnClickListener(this);
        security.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId())
        {
            case R.id.stu_card:i=new Intent(this,Stu_login.class);startActivity(i);break;
            case R.id.admin_card:i=new Intent(this,admin_login.class);startActivity(i);break;
            case R.id.war_card:i=new Intent(this,warden_login.class);startActivity(i);break;
            case R.id.sec_card:i=new Intent(this,security_login.class);startActivity(i);break;
            default:break;        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}
