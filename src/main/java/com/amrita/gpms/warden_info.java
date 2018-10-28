package com.amrita.gpms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.amrita.gpms.warden_login.user_war;

public class warden_info extends AppCompatActivity {

   private RecyclerView recyclerView;
    private item_gate_adapter adapter;
    private List<item_gate> iglist;
    private ProgressDialog progress1;
    public static boolean warret=false;
  //  String gid, regno, outdate, indate, reason, approval;
    android.support.v7.widget.Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_info);
        if(warret) {
            warret=false;
            Snackbar.make(findViewById(R.id.ward_log2), "Login successful", Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(android.R.color.holo_green_dark)).show();
        }
        tb=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        recyclerView = (RecyclerView) findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        iglist = new ArrayList<>();
       showdialog();
        loadgatepass();

    }

    private void loadgatepass() {
         String url = "http://reddysaisumanth014.000webhostapp.com/warden_view.php";
        StringRequest sr = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, url, new Response.Listener<String>() {
            //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {
                canceldialog();
                try {

                    JSONArray gparray = new JSONArray(response);
                    for (int i = 0; i < gparray.length(); i++) {
                        JSONObject gpobject = gparray.getJSONObject(i);
                        iglist.add(new item_gate(
                                gpobject.getString("Regno"),
                                gpobject.getString("Approval"),
                                gpobject.getInt("GID")

                        ));
                    }
                    adapter = new item_gate_adapter(warden_info.this, iglist);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // canceldialog();
                Toast.makeText(getApplicationContext(), "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("user", user_war);
                    return params;
                }

        };
        sr.setRetryPolicy(new DefaultRetryPolicy(5000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(sr);

    }
    private  void  showdialog()
    {    progress1 = new ProgressDialog(this);

        progress1.setTitle("Fetching Data");
        progress1.setMessage("Please wait...");
        progress1.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress1.show();
    }
    private  void  canceldialog()
    {
        progress1.dismiss();
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.settings)
        {   user_war="";
            MainActivity.ret=true;
            Intent intent=new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
        return true;
    }
}