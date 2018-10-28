package com.amrita.gpms;

/**
 * Created by Sumanth on 10/3/2018.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class adtabwar1 extends Fragment {
    private EditText w,p,n,ph;
    private Button b;
    private String wname,wphone,wpasswd,wid;
    private long i;
    private ProgressDialog progress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.adtab_war, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        w=(EditText)view.findViewById(R.id.et_adwarid);
        p=(EditText)view.findViewById(R.id.et_adwarpwd);
        n=(EditText)view.findViewById(R.id.et_adwarname);
        ph=(EditText)view.findViewById(R.id.et_adwarphone);
        b=(Button)view.findViewById(R.id.bt_adwaradd);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
                showdialog();
                senddata();
            }
        });
    }
    private void senddata()
    {     wid=w.getText().toString().trim();
        wname=n.getText().toString().trim();
        wphone=ph.getText().toString().trim();
        wpasswd=p.getText().toString().trim();
        //year=y.getText().toString().trim();
        //phone=ph.getText().toString().trim();
        i=Long.parseLong(wphone);
        String url="http://reddysaisumanth014.000webhostapp.com/add_war.php";
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                w.setText("");
                n.setText("");
                ph.setText("");
                p.setText("");
                canceldialog();
                if(response.equals("success"))
                {
                    Snackbar.make(getActivity().findViewById(R.id.adtabwar),"Warden added successfully.", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();
//  Toast.makeText(getApplicationContext(),"Login successful!",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(getApplicationContext(),"Invalid username or password!",Toast.LENGTH_LONG).show();
                    Snackbar.make(getActivity().findViewById(R.id.adtabwar),"Failed to add user.", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                w.setText("");
                n.setText("");
                ph.setText("");
                p.setText("");
                canceldialog();
                //Toast.makeText(getApplicationContext(),"error:"+error.toString(),Toast.LENGTH_LONG).show();
                Snackbar.make(getActivity().findViewById(R.id.adtabwar),"Error:"+error, Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("Wid",wid);
                params.put("Name",wname);
                //params.put("Dept",dept);
                //params.put("Year",year);
                params.put("Phone",""+i);
                params.put("Passwd",wpasswd);
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