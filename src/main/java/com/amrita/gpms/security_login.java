package com.amrita.gpms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class security_login extends AppCompatActivity {
private EditText gpno;
private Button check;
private  String s;
static  int gp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_login);
        gpno=(EditText)findViewById(R.id.et_gpno);
        check=(Button) findViewById(R.id.bt_check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=gpno.getText().toString();
                gp=Integer.valueOf(s);
                close();
                openIntent();
            }
        });
    }
    private void  close()
    {
        View view=this.getCurrentFocus();
        InputMethodManager i=(InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        i.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    @Override
    public void onBackPressed() {
        Intent intent;
        intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void openIntent()
    {
        Intent intent=new Intent(this,security_info.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
