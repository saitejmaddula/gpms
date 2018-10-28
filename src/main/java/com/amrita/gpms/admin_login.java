package com.amrita.gpms;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
private EditText username,psswd;
private String user,pwd;
private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        username=(EditText)findViewById(R.id.et_aduser);
        psswd=(EditText)findViewById(R.id.et_adpsswd);

        b=(Button)findViewById(R.id.bt_adlogin);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
                user=username.getText().toString();
                pwd=psswd.getText().toString();

                if(user.equals("admin")&&pwd.equals("admin"))
                {
                    //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
                    openIntent();
                }
                else {
                    Snackbar.make(findViewById(R.id.admin_log),"Invalid username or password", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();

                }
            }
        });

    }
private void openIntent()
{
    Intent intent;
    intent=new Intent(this,admin_info.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
}
    @Override
    public void onBackPressed() {
        Intent intent;
        intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void  close()
    {
        View view=this.getCurrentFocus();
        InputMethodManager i=(InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        i.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
