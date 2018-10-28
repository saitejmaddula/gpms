package com.amrita.gpms;

/**
 * Created by Sumanth on 10/3/2018.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


public  class adtab_stu1 extends Fragment
{
    private EditText un,n,p,ph,d,y;
    private Button b;
    private String regno,passwd,dept,year,phone,name;
    private  long i;
    ProgressDialog progress;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.adtab_stu, container, false);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        un=(EditText)view.findViewById(R.id.et_adname);
        n=(EditText)view.findViewById(R.id.et_adstuname);
        p=(EditText)view.findViewById(R.id.et_adpd);
        ph=(EditText)view.findViewById(R.id.et_adphone);
        d=(EditText)view.findViewById(R.id.et_addept);
        y=(EditText)view.findViewById(R.id.et_adyear);
        b=(Button)view.findViewById(R.id.bt_addstu);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
                showdialog();
                senddata();//Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void senddata()
    {     regno=un.getText().toString().trim();
         passwd=p.getText().toString().trim();
        name=n.getText().toString().trim();
          dept=d.getText().toString().trim();
          year=y.getText().toString().trim();
          phone=ph.getText().toString().trim();
          i=Long.parseLong(phone);
        String url="http://reddysaisumanth014.000webhostapp.com/add_stu.php";
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                un.setText("");
                p.setText("");
                n.setText("");
                d.setText("");
                y.setText("");
                ph.setText("");
                canceldialog();

                if(response.equals("success"))
                {
                    Snackbar.make(getActivity().findViewById(R.id.adtabstu),"Student added successfully.", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();
//  Toast.makeText(getApplicationContext(),"Login successful!",Toast.LENGTH_SHORT).show();
                }
                else{

                    //Toast.makeText(getApplicationContext(),"Invalid username or password!",Toast.LENGTH_LONG).show();
                    Snackbar.make(getActivity().findViewById(R.id.adtabstu),"Failed to add user.", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                un.setText("");
                p.setText("");
                n.setText("");
                d.setText("");
                y.setText("");
                ph.setText("");
                canceldialog();
                //Toast.makeText(getApplicationContext(),"error:"+error.toString(),Toast.LENGTH_LONG).show();
                Snackbar.make(getActivity().findViewById(R.id.adtabstu),"Error:"+error, Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("Regno",regno);
                params.put("Name",name);
                params.put("Dept",dept);
                params.put("Year",year);
                params.put("Phone",""+i);
                params.put("Passwd",passwd);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private  void  showdialog()
    {    progress = new ProgressDialog(getContext());

        progress.setTitle("Loading");
        progress.setMessage("Authenticating...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }
    private  void  canceldialog()
    {
        progress.dismiss();
    }
    private void  close()
    {
        View view=getActivity().getCurrentFocus();
        InputMethodManager i=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        i.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}

