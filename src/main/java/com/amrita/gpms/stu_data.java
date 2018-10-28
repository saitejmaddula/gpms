package com.amrita.gpms;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import static com.amrita.gpms.stu_adapter.bb;

public class stu_data extends AppCompatActivity {
    private TextView r,g,out,in,rea,ap,wid;
    private Button b;
    private ProgressDialog progress3;
    private SimpleDateFormat sdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_data);
        r=(TextView)findViewById(R.id.tv_neregno1);
        g=(TextView)findViewById(R.id.tv_negid1);
        out=(TextView)findViewById(R.id.tv_neout1);
        in=(TextView)findViewById(R.id.tv_nein1);
        rea=(TextView)findViewById(R.id.tv_nerea1);
        ap=(TextView)findViewById(R.id.tv_nestatus1);
        wid=(TextView)findViewById(R.id.tv_newid);
        getinfo();
        b=(Button)findViewById(R.id.bt_ok);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void getinfo()
    {   showdialog();
        String url="http://reddysaisumanth014.000webhostapp.com/stugpinfo.php";
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
                        wid.setText(String.valueOf(gpobi.getString("WID")));
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
                params.put("GID",""+bb);
                return params;
            }
        };
        requestQueue.add(stringRequest);
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
