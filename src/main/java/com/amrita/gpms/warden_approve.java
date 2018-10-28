package com.amrita.gpms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.amrita.gpms.item_gate_adapter.b;

public class warden_approve extends AppCompatActivity {
  private   TextView r,g,out,in,rea,ap;
   private Button ba,bh,br;
    private ProgressDialog progress3;
    private SimpleDateFormat sdf;
     AlertDialog alertDialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_approve);
        warden_info.warret=false;
        r=(TextView)findViewById(R.id.tv_neregno_s);
        g=(TextView)findViewById(R.id.tv_negid_s);
        out=(TextView)findViewById(R.id.tv_neoutdate_s);
        in=(TextView)findViewById(R.id.tv_neindate_s);
        rea=(TextView)findViewById(R.id.tv_nereason_s);
        ap=(TextView)findViewById(R.id.tv_nestatus1);
        ba=(Button)findViewById(R.id.bt_approve);
        bh=(Button)findViewById(R.id.bt_hold);
        br=(Button)findViewById(R.id.bt_reject);
        getinfo();
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approve();
            }
        });
        bh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hold();
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reject();
            }
        });
    }
    private void getinfo()
    {   showdialog();
        String url="http://reddysaisumanth014.000webhostapp.com/appr.php";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    try {
                        canceldialog();
                        JSONArray gparray = new JSONArray(response);
                        for(int i=0;i<gparray.length();i++) {
                            JSONObject gpobi = gparray.getJSONObject(i);
                            //Toast.makeText(getApplicationContext(), "" + String.valueOf(gpobi.getString("Outdate")), Toast.LENGTH_SHORT).show();
                            rea.setText(String.valueOf(gpobi.getString("Reason")));
                            ap.setText(String.valueOf(gpobi.getString("Approval")));
                            r.setText(String.valueOf(gpobi.getString("Regno")));
                            g.setText(String.valueOf(gpobi.getInt("GID")));
                            sdf=new SimpleDateFormat("yyyy-MM-dd");
                            String a=gpobi.getString("Oudate");
                            String b=gpobi.getString("Indate");
                            out.setText(a);
                            in.setText(b);
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        canceldialog();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("GID",""+b);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void approve() {
        showdialog();
        String url = "http://reddysaisumanth014.000webhostapp.com/war_appr.php";
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("success")) {
                    canceldialog();
                   /*alertDialog2 = new AlertDialog.Builder(warden_approve.this).create();
                    alertDialog2.setTitle("Successful");
                    alertDialog2.setMessage("Gatepass approved successfully!!!");
                    alertDialog2.setIcon(R.drawable.tick);
                    alertDialog2.show();*/
                    Toast.makeText(getApplicationContext(),"Update successful!!!",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        canceldialog();
                        Toast.makeText(getApplicationContext(), "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("GID",""+b);
                return params;
            }
        };
        requestQueue1.add(stringRequest1);
    }
    private void hold() {
        showdialog();
        String url = "http://reddysaisumanth014.000webhostapp.com/war_hold.php";
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    canceldialog();
                if (response.equals("success")) {
                  /*   alertDialog2 = new AlertDialog.Builder(warden_approve.this).create();
                    alertDialog2.setTitle("Successful");
                    alertDialog2.setMessage("Status updated successfully!!!");
                    alertDialog2.setIcon(R.drawable.tick);
                    alertDialog2.show();*/
                    Toast.makeText(getApplicationContext(),"Update successful!!!",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        canceldialog();
                        Toast.makeText(getApplicationContext(), "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("GID",""+b);
                return params;
            }
        };
        requestQueue2.add(stringRequest2);
    }
    private void reject() {
        showdialog();
        String url = "http://reddysaisumanth014.000webhostapp.com/war_reject.php";
        RequestQueue requestQueue3 = Volley.newRequestQueue(this);
        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    canceldialog();
                if (response.equals("success")) {
                     /* alertDialog2 = new AlertDialog.Builder(warden_approve.this).create();
                    alertDialog2.setTitle("Successful");
                    alertDialog2.setMessage("Status updated successfully!!!");
                    alertDialog2.setIcon(R.drawable.tick);
                    alertDialog2.show();*/
                    Toast.makeText(getApplicationContext(),"Update successful!!!",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        canceldialog();
                        Toast.makeText(getApplicationContext(), "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("GID",""+b);
                return params;
            }
        };
        requestQueue3.add(stringRequest3);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,warden_info.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(intent);
        finishAfterTransition();
    }
    private  void  showdialog()
    {    progress3= new ProgressDialog(this);

        progress3.setTitle("Loading");
        progress3.setMessage("Processing...");
        progress3.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress3.show();
    }
    private  void  canceldialog()
    {
        progress3.dismiss();
    }

}
