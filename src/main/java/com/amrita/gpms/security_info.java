package com.amrita.gpms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.amrita.gpms.warden_login.user_war;

public class security_info extends AppCompatActivity {
private TextView r,w,g,ind,out,rea,sta;
private Button b;
private  ProgressDialog progress1;
private SimpleDateFormat sdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_info);
        r=(TextView)findViewById(R.id.tv_secreg);
        w=(TextView)findViewById(R.id.tv_secwid);
        g=(TextView)findViewById(R.id.tv_secgid);
        ind=(TextView)findViewById(R.id.tv_secindate);
        out=(TextView)findViewById(R.id.tv_secoutdate);
        rea=(TextView)findViewById(R.id.tv_secrea);
        sta=(TextView)findViewById(R.id.tv_secstatus);
        b=(Button)findViewById(R.id.bt_oksec);
        getdata();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private  void getdata()
    {   showdialog();
        String url = "http://reddysaisumanth014.000webhostapp.com/sec_view.php";
        StringRequest sr = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {
                try {
                    canceldialog();
                    JSONArray gparray = new JSONArray(response);
                    if(gparray.isNull(0))
                    {
                        Toast.makeText(getApplicationContext(),"Invalid gid",Toast.LENGTH_SHORT).show();
                    }
                    for(int i=0;i<gparray.length();i++) {
                        JSONObject gpobi = gparray.getJSONObject(i);
                        if(String.valueOf(gpobi.getString("Approval")).equals("NOT_APPROVED"))
                            sta.setTextColor(Color.rgb(0,0,255));
                        if(String.valueOf(gpobi.getString("Approval")).equals("APPROVED"))
                            sta.setTextColor(Color.rgb(0,255,0));
                        if(String.valueOf(gpobi.getString("Approval")).equals("HOLD"))
                            sta.setTextColor(Color.rgb(255,255,0));
                        if(String.valueOf(gpobi.getString("Approval")).equals("REJECT"))
                            sta.setTextColor(Color.rgb(255,0,0));
                        //Toast.makeText(getApplicationContext(), "" + String.valueOf(gpobi.getString("Outdate")), Toast.LENGTH_SHORT).show();
                        rea.setText(String.valueOf(gpobi.getString("Reason")));
                        sta.setText(String.valueOf(gpobi.getString("Approval")));
                        r.setText(String.valueOf(gpobi.getString("Regno")));
                        g.setText(String.valueOf(gpobi.getInt("GID")));
                        w.setText(String.valueOf(gpobi.getString("WID")));
                        sdf=new SimpleDateFormat("yyyy-MM-dd");
                        String a=gpobi.getString("Oudate");
                        String b=gpobi.getString("Indate");
                        out.setText(a);
                        ind.setText(b);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                canceldialog();
                Toast.makeText(getApplicationContext(), "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("gid",""+security_login.gp);
                return params;
            }

        };
        sr.setRetryPolicy(new DefaultRetryPolicy(3000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(sr);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,security_login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

}
